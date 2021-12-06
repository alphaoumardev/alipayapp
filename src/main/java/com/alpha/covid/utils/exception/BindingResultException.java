package com.alpha.covid.utils.exception;

import com.alpha.covid.utils.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BindingResultException extends RuntimeException
{

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "错误消息")
    private String errMsg;

    public BindingResultException(BindingResult result)
    {
        List<String> errors = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        this.code = ResultCode.PARAM_ERROR.getCode();
        this.errMsg = ResultCode.PARAM_ERROR.getMessage() + "[ " + String.join(",", errors) + " ]";
    }
}
