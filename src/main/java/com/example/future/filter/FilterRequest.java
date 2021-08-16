package com.example.future.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {

    private String fieldName;
    private FilterRequestOperationEnums operation;
    private String [] values;

}
