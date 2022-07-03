package com.rockvine.algorithm.twopointers;

/**
 * @author rocky
 * @date 2022-06-29 23:44
 * @description 环形链表 II（https://leetcode.cn/problems/linked-list-cycle-ii/）
 */
public class LinkedListCycleII {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;

        Solution solution = new Solution();
        ListNode listNode = solution.detectCycle(head);
        System.out.println(listNode != null ? listNode.val : null);
    }

    static class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode fast = head, slow = head;
            // 判断是否存在环形
            do {
                if (fast == null || fast.next == null) {
                    return null;
                }
                fast = fast.next.next;
                slow = slow.next;
            } while (fast != slow);

            // 如果存在，查找环形节点
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
