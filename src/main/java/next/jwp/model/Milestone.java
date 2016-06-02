package next.jwp.model;

import java.sql.Date;

public class Milestone {
    private int id;
    private String subject;
    private Date startDate;
    private Date endDate;
    
    private int issuesNum;
    private int openIssuesNum;

    public Milestone() {
        super();
    }

    public Milestone(String subject) {
        super();
        this.subject = subject;
    }

    public Milestone(int id, String subject, Date startDate, Date endDate) {
        super();
        this.id = id;
        this.subject = subject;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public int getIssuesNum() {
        return issuesNum;
    }

    public void setIssuesNum(int issuesNum) {
        this.issuesNum = issuesNum;
    }

    public int getOpenIssuesNum() {
        return openIssuesNum;
    }

    public void setOpenIssuesNum(int openIssuesNum) {
        this.openIssuesNum = openIssuesNum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + id;
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
        Milestone other = (Milestone) obj;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (id != other.id)
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Milestone [id=" + id + ", subject=" + subject + ", startDate=" + startDate + ", endDate=" + endDate
                + "]";
    }

}
