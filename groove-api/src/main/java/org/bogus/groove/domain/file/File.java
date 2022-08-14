package org.bogus.groove.domain.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.common.enumeration.FileType;

@Getter
@AllArgsConstructor
@ToString
public class File {
    private Long id;

    private String path;

    private Integer size;

    private String extension;

    private FileType fileType;
}
