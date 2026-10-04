package com.example.learn.struts2.demo35;

import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 单元测试：通过公共 {@link StrutsJUnit5Test} 基类跑。
 * <p>
 * 注：父类的 setUp/tearDown 标注的是 Struts2 自有 @Before/@After（不会被自动调用），
 * {@link StrutsJUnit5Test} 改用 JUnit 5 的 @BeforeEach/@AfterEach 包一层。
 */
public class LongTaskActionTest extends StrutsJUnit5Test {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @BeforeEach
    public void resetStore() {
        TaskStore.clear();
    }

    @AfterAll
    public static void shutdownStore() {
        TaskStore.shutdown();
    }

    /**
     * execAndWait 拦截器第一次请求应当返回 "wait"。
     * 真实流程：拦截器启动后台 BackgroundProcess → 立即返回 "wait" → 渲染 wait.jsp。
     * 然后等待后台 execute() 跑完，验证 TaskStore 状态。
     */
    @Test
    public void testLongTaskStartsAsyncAndReturnsWait() throws Exception {
        String result = executeAction("/async/long-task.action");
        assertEquals("wait", result, "execAndWait 拦截器第一次请求应返回 wait");

        LongTaskAction action = (LongTaskAction) actionInvocation.getAction();
        assertNotNull(action.getTaskId(), "execute() 应已生成 taskId");

        // 等后台 execute() 跑完（最多 5 秒：实际 ~2 秒）
        String taskId = action.getTaskId();
        long deadline = System.currentTimeMillis() + 5_000;
        TaskStore.TaskStatus s;
        do {
            TimeUnit.MILLISECONDS.sleep(50);
            s = TaskStore.get(taskId);
        } while (s != null && !s.isComplete() && System.currentTimeMillis() < deadline);

        assertNotNull(s, "TaskStore 应能查到 taskId 对应的状态");
        assertTrue(s.isComplete(), "后台 execute() 应在 5 秒内跑完");
        assertEquals(100, s.getProgress(), "execute() 跑完后 progress 应为 100");
        assertNotNull(s.getResult(), "execute() 跑完后应设置 result");
    }

    @Test
    public void testProgressActionReportsStatus() {
        TaskStore.TaskStatus s = TaskStore.create();
        s.setProgress(50);
        s.setResult("中间结果");

        ProgressAction pa = new ProgressAction();
        pa.setTaskId(s.getTaskId());
        assertEquals("success", pa.execute());

        assertEquals(50, pa.getProgress());
        assertFalse(pa.isComplete());
        assertEquals("中间结果", pa.getResult());
    }

    @Test
    public void testProgressActionReturnsZeroForUnknownTask() {
        ProgressAction pa = new ProgressAction();
        pa.setTaskId("non-existent-id");
        assertEquals("success", pa.execute());
        assertEquals(0, pa.getProgress());
        assertFalse(pa.isComplete());
    }

    @Test
    public void testTaskCreateActionReturnsSuccessWithTaskId() {
        TaskCreateAction action = new TaskCreateAction();
        String result = action.execute();
        assertEquals("success", result);
        assertNotNull(action.getTaskId());

        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        TaskStore.TaskStatus s = TaskStore.get(action.getTaskId());
        assertNotNull(s);
        assertTrue(s.getProgress() > 0);
    }

    /**
     * TaskStore TTL 清理：把 lastUpdatedAt 改成 "11 分钟前"，cleanupExpired() 应清掉。
     */
    @Test
    public void testTaskStoreExpiresOldCompletedEntries() throws Exception {
        TaskStore.TaskStatus s = TaskStore.create();
        s.setProgress(100);
        s.setResult("done");
        s.setComplete(true);

        long old = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(11);
        setLastUpdatedAt(s, old);

        int removed = TaskStore.cleanupExpired();
        assertEquals(1, removed);
        assertEquals(0, TaskStore.size());
    }

    @Test
    public void testTaskStoreKeepsRecentCompletedEntries() {
        TaskStore.TaskStatus s = TaskStore.create();
        s.setProgress(100);
        s.setComplete(true);

        int removed = TaskStore.cleanupExpired();
        assertEquals(0, removed);
        assertEquals(1, TaskStore.size());
    }

    @Test
    public void testTaskStoreKeepsIncompleteOldEntries() throws Exception {
        TaskStore.TaskStatus s = TaskStore.create();
        s.setProgress(50);
        long old = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(60);
        setLastUpdatedAt(s, old);

        int removed = TaskStore.cleanupExpired();
        assertEquals(0, removed, "未完成的不应清");
        assertEquals(1, TaskStore.size());
    }

    @Test
    public void testTaskStoreTouchBumpsLastUpdatedAt() throws Exception {
        TaskStore.TaskStatus s = TaskStore.create();
        long old = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5);
        setLastUpdatedAt(s, old);
        assertEquals(old, s.getLastUpdatedAt());

        TimeUnit.MILLISECONDS.sleep(5);
        s.touch();
        assertTrue(s.getLastUpdatedAt() > old);
    }

    @Test
    public void testTaskRunnerRunsToCompletion() {
        String taskId = TaskStore.create().getTaskId();
        TaskRunner.run(taskId);

        TaskStore.TaskStatus s = TaskStore.get(taskId);
        assertNotNull(s);
        assertTrue(s.isComplete());
        assertEquals(100, s.getProgress());
        assertNotNull(s.getResult());
    }

    private static void setLastUpdatedAt(TaskStore.TaskStatus s, long value) throws Exception {
        Field f = TaskStore.TaskStatus.class.getDeclaredField("lastUpdatedAt");
        f.setAccessible(true);
        f.setLong(s, value);
    }
}