package src;

import java.time.LocalDateTime;

public class QueueNode {
    Member member;
    LocalDateTime entryTime;
    QueueNode next;

    public QueueNode(Member member, LocalDateTime entryTime) {
        this.member = member;
        this.entryTime = entryTime;
        this.next = null;
    }
}
