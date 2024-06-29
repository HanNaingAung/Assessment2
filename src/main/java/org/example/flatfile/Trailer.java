package org.example.flatfile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*@Getter
@Setter
@ToString*/
public class Trailer {
    private String recordType;
    private String fileType;
    private String padding1;
    private String padding2;

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getPadding1() {
        return padding1;
    }

    public void setPadding1(String padding1) {
        this.padding1 = padding1;
    }

    public String getPadding2() {
        return padding2;
    }

    public void setPadding2(String padding2) {
        this.padding2 = padding2;
    }
}
