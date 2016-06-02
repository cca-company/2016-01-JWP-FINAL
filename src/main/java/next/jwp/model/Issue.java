package next.jwp.model;

import java.sql.Date;

public class Issue {
    private int id;
    private int milestoneId;
    private int writerId;
    private String subject;
    private String comment;
    private boolean isOpen;
    private Date date;
    private int labelId;
    private int assigneeId;
    
    private int commentNum;
    private String writerName;
    private String writerPic;
    
    public Issue() {
        super();
    }
    
    public Issue(int writerId, int milestoneId, String subject, String comment) {
        super();
        this.writerId = writerId;
        this.milestoneId = milestoneId;
        this.subject = subject;
        this.isOpen = true;
        this.comment = comment;
    }
    
    public Issue(int id, int writerId, int milestoneId, String subject, boolean isOpen, String comment, Date date) {
        super();
        this.id = id;
        this.milestoneId = milestoneId;
        this.writerId = writerId;
        this.subject = subject;
        this.isOpen = isOpen;
        this.comment = comment;
        this.date = date;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMilestoneId() {
        return milestoneId;
    }
    public void setMilestoneId(int milestoneId) {
        this.milestoneId = milestoneId;
    }
    public int getWriterId() {
        return writerId;
    }
    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public boolean getIsOpen() {
        return isOpen;
    }
    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }
    
    public String getWriterPic() {
        return writerPic;
    }

    public void setWriterPic(String writerPic) {
        this.writerPic = writerPic;
    }
    
    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Issue other = (Issue) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Issue [id=" + id + ", writerId=" + writerId + ", milestoneId=" + milestoneId + ", subject=" + subject + ", isOpen=" + isOpen + ", comment=" + comment + ", date=" + date + "]";
    }
    
    
}
