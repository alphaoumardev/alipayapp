package com.alpha.covid.utils.exception;


import com.alpha.covid.utils.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException
{
    @ApiModelProperty(value = "Status code")
    private Integer code;

    @ApiModelProperty(value = "error message")
    private String errorMessage;

    public BusinessException(ResultCode resultCode)
    {
        this.code = resultCode.getCode();
        this.errorMessage = resultCode.getMessage();
    }

    public BusinessException(ResultCode resultCode, Exception e)
    {
        this.code = resultCode.getCode();
        this.errorMessage = resultCode.getMessage() + e.getMessage();
    }

    public BusinessException(String msg)
    {
        this.code = ResultCode.FAILED.getCode();
        this.errorMessage = msg;
    }

    public BusinessException(ResultCode resultCode, String errorMessage)
    {
        this.code = resultCode.getCode();
        this.errorMessage = BusinessException.this.errorMessage;
    }
}
