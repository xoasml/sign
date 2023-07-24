package com.basic.sign.util.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Response<T> {
    private List<T> resultSet = new ArrayList<>();

    // todo response 에 request 데이터 담기

    private int resultCnt;

    private String resultMsg = "";

    public Response(List<T> resultSet) {
        this.resultSet = resultSet;
        this.resultCnt = resultSet.size();
        this.resultMsg = "정상 처리입니다.";
    }

    public Response(T result) {
        if(result != null){
            this.resultCnt = 1;
            this.resultSet.add(result);
            this.resultMsg = "정상 처리입니다.";
        } else {
            this.resultCnt = 0;
            this.resultMsg = "데이터가 없습니다.";
        }
    }



    public void setResultMsg(String msg) {
        this.resultMsg = msg;
    }
}
