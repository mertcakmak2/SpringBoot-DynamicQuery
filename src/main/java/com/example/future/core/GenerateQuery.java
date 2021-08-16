package com.example.future.core;

import com.example.future.filter.FilterRequest;

import java.util.ArrayList;
import java.util.List;

public class GenerateQuery {

    public static String getQuery(List<FilterRequest> filterRequests, String filterType, String entity){

        List<String> queryArray = new ArrayList<>();

        for (FilterRequest filterRequest : filterRequests) {

            switch (filterRequest.getOperation()) {
                case EQ:
                    String[] values = filterRequest.getValues();
                    String value = values[0];
                    queryArray.add(filterRequest.getFieldName() + " = " + "'" + value + "'");
                    break;
                case NOTEQ:
                    String[] notEqValues = filterRequest.getValues();
                    String notEqValue = notEqValues[0];
                    queryArray.add(filterRequest.getFieldName() + " != " + "'" + notEqValue + "'");
                    break;
                case IN:
                    String[] inValues = filterRequest.getValues();
                    for (int i = 0; i < inValues.length; i++) {
                        inValues[i] = String.format("'%s'", inValues[i]);
                    }
                    String strValues = String.join(",", inValues);
                    queryArray.add(filterRequest.getFieldName() + " in (" + strValues + ") ");
                    break;
                case NOTIN:
                    String[] notInValues = filterRequest.getValues();
                    for (int i = 0; i < notInValues.length; i++) {
                        notInValues[i] = String.format("'%s'", notInValues[i]);
                    }
                    String notInValue = String.join(",", notInValues);
                    queryArray.add(filterRequest.getFieldName() + " not in (" + notInValue + ") ");
                    break;
                case LESS:
                    String[] lessValue = filterRequest.getValues();
                    queryArray.add(filterRequest.getFieldName() + " < " + String.format("'%s'", lessValue[0]));
                    break;
                case GREATHER:
                    String[] greatherValue = filterRequest.getValues();
                    queryArray.add(filterRequest.getFieldName() + " > " + String.format("'%s'", greatherValue[0]));
                    break;
                case LIKE:
                    String[] likeValues = filterRequest.getValues();
                    String likeStr ="'%"+ likeValues[0] +"%'";
                    queryArray.add(filterRequest.getFieldName() + " LIKE " + likeStr);
                    break;
                default:
                    break;
            }

        }
        String type = String.format(" %s ", filterType);
        String query = String.join(type, queryArray);
        query = "select u from " + entity + " u where " + query;
        System.out.println(query);

        return query;
    }
}
