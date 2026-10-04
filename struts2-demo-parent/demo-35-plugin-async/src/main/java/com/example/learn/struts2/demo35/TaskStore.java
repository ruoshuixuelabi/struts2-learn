package com.example.learn.struts2.demo35;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 极简任务状态存储器（demo-35 演示用）。
 * <p>
 * 真实场景应替换为 Redis / DB，多实例间共享。本 demo 限定为进程内，便于单元测试。
 *
 * <h2>TTL 自动清理</h2>
 * 后台守护线程每分钟扫描一次，删除已 {@link TaskStatus#isComplete()} 完成
 * 且距最后一次更新超过 10 分钟的记录，避免长跑 demo 时 {@code STORE} 无限增长。
 * 生产环境建议替换为 Redis / DB，TTL 用数据库自身机制或 Keyspace Notifications。
 */
public final class TaskStore {

    /** 已完成任务保留时间（demo 默认 10 分钟，生产可调大） */
    public static final long COMPLETED_TTL_MILLIS = TimeUnit.MINUTES.toMillis(10);

    public static final class TaskStatus {
        private final String taskId;
        private volatile int progress;
        private volatile boolean complete;
        private volatile String result;
        private final long createdAt;
        private volatile long lastUpdatedAt;

        TaskStatus(String taskId) {
            this.taskId = taskId;
            long now = System.currentTimeMillis();
            this.createdAt = now;
            this.lastUpdatedAt = now;
        }

        public String getTaskId() { return taskId; }
        public int getProgress() { return progress; }
        public boolean isComplete() { return complete; }
        public String getResult() { return result; }
        public long getCreatedAt() { return createdAt; }
        public long getLastUpdatedAt() { return lastUpdatedAt; }

        void setProgress(int p) { this.progress = p; this.lastUpdatedAt = System.currentTimeMillis(); }
        void setComplete(boolean c) { this.complete = c; this.lastUpdatedAt = System.currentTimeMillis(); }
        void setResult(String r) { this.result = r; this.lastUpdatedAt = System.currentTimeMillis(); }

        /** 显式 touch（让 TTL 计时从此刻起重新计算） */
        public void touch() { this.lastUpdatedAt = System.currentTimeMillis(); }
    }

    private static final Map<String, TaskStatus> STORE = new ConcurrentHashMap<>();

    private static final ScheduledExecutorService CLEANER =
        Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "taskstore-cleaner");
            t.setDaemon(true);
            return t;
        });

    static {
        // 每 1 分钟扫描一次，删除完成 + 超过 TTL 的记录
        CLEANER.scheduleAtFixedRate(TaskStore::cleanupExpired,
            1, 1, TimeUnit.MINUTES);
    }

    private TaskStore() {}

    public static TaskStatus create() {
        TaskStatus status = new TaskStatus(UUID.randomUUID().toString());
        STORE.put(status.getTaskId(), status);
        return status;
    }

    public static TaskStatus get(String taskId) {
        return STORE.get(taskId);
    }

    public static void updateProgress(String taskId, int p) {
        TaskStatus s = STORE.get(taskId);
        if (s != null) s.setProgress(p);
    }

    public static void markComplete(String taskId, String result) {
        TaskStatus s = STORE.get(taskId);
        if (s != null) {
            s.setProgress(100);
            s.setResult(result);
            s.setComplete(true);
        }
    }

    /**
     * 清理已完成且超过 TTL 的任务记录。
     * 暴露为 public 是为了方便测试和运维触发。
     */
    public static int cleanupExpired() {
        long threshold = System.currentTimeMillis() - COMPLETED_TTL_MILLIS;
        int before = STORE.size();
        STORE.entrySet().removeIf(e -> {
            TaskStatus s = e.getValue();
            return s.isComplete() && s.getLastUpdatedAt() < threshold;
        });
        return before - STORE.size();
    }

    /** 测试用：清理所有任务状态 */
    public static void clear() {
        STORE.clear();
    }

    /** 测试 / 关闭钩子用：停止清理守护线程 */
    public static void shutdown() {
        CLEANER.shutdownNow();
    }

    /** 当前任务数（测试 / 监控用） */
    public static int size() {
        return STORE.size();
    }
}