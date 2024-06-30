package org.example.flatfile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Header {
    private String recordType;
    private String fileType;
    private String padding1;
    private String padding2;

}
