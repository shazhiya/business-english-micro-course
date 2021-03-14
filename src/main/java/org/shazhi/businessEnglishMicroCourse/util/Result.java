package org.shazhi.businessEnglishMicroCourse.util;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result {
    int code = 0;
    String msg;
    Object data;

    public Result setSuccess(String msg){
        this.msg = msg;
        this.code  = 1;
        return this;
    }

    public Result setSuccess(){
        return this.setCode(1);
    }
}
