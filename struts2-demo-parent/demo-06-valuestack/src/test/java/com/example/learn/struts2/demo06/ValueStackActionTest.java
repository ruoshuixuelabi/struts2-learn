package com.example.learn.struts2.demo06;

import org.apache.struts2.StrutsJUnit5Test;
import org.apache.struts2.action.Action;
import org.apache.struts2.ActionContext;
import org.apache.struts2.util.ValueStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValueStackActionTest extends StrutsJUnit5Test<ValueStackAction> {

    @Override
    protected String getConfigPath() {
        return "struts.xml";
    }

    @Test
    public void testValueStackAction() throws Exception {
        String result = executeAction("/stack.action");
        assertEquals(Action.SUCCESS, result);

        ValueStack stack = ActionContext.getContext().getValueStack();
        // Struts 7.x：stack.set(key, value) 会把 value 包装成一个 HashMap "virtual compound root entry"
        // 推到栈顶，因此 push 进去的 ExtraInfo 实际上位于栈顶之下 1 位。
        // 这里用 pop 取出前两个对象，验证 ExtraInfo 真的进了栈。
        Object first = stack.pop();
        Object second = stack.pop();
        // 恢复栈（避免污染后续观察）
        stack.push(second);
        stack.push(first);

        boolean foundExtra = false;
        for (Object o : new Object[]{first, second}) {
            if (o instanceof ExtraInfo) {
                foundExtra = true;
                break;
            }
            // 第一层 HashMap 里携带 set 注册的对象名
            if (o instanceof java.util.Map) {
                java.util.Map<?, ?> m = (java.util.Map<?, ?>) o;
                if (m.containsKey("greeting")) {
                    // 这是 stack.set 注入的 entry，里面的对象是 "world-from-set"，不是 ExtraInfo
                }
            }
        }
        assertTrue(foundExtra, "push 进去的 ExtraInfo 应该在值栈里（栈顶是 stack.set 包装的 HashMap）");
    }
}
