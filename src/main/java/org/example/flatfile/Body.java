package org.example.flatfile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*@Getter
@Setter
@ToString*/
public class Body {
    private String referenceNo;
    private String amount;
    private String padding1;
    private String padding2;

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
