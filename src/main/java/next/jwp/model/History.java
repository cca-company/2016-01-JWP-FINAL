package next.jwp.model;

import java.sql.Date;

public class History {
    private int id;
    private int issueId;
    private int writerId;
    private String type;
    private String content;
    private Date date;
    
    private String writerName;
    private String writerPic;

    public History() {
        super();
    }

    public History(int issueId, int writerId, String type, String content) {
        super();
        this.issueId = issueId;
        this.writerId = writerId;
        this.type = type;
        this.content = content;
    }
    
    public History(int id, int issueId, int writerId, String type, String content, Date date) {
        super();
        this.id = id;
        this.issueId = issueId;
        this.writerId = writerId;
        this.type = type;
        this.content = content;
        this.date = date;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIssueId() {
        return issueId;
    }
    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }
    public int getWriterId() {
        return writerId;
    }
    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + issueId;
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
        History other = (History) obj;
        if (id != other.id)
            return false;
        if (issueId != other.issueId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "History [id=" + id + ", issueId=" + issueId + ", writerId=" + writerId + ", type=" + type + ", content="
                + content + ", date=" + date + "]";
    }
    
    
}
