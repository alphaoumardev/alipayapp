package com.alpha.covid.structor.form;

import lombok.Data;

@Data
public class LoginLogsForm
{
    private Integer page;
    private Integer size;
    private String username;
    private String ip;
    private String location;
}
