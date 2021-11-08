package com.jbc.message.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(description  = "String wrapper class, for successful responses")
public class SuccessResponse {

	@ApiModelProperty(value = "Success message")
	private String response;
}
