package com.data_integration.b.service.election;

import com.data_integration.b.pojo.election.Election;

import java.util.List;

public interface ElectionService {

    /**获取某学生所选择的课程信息*/
    List<Election> getElectionsBySid(String sid);

    /**添加选课记录*/
    int addElectionBySidCid(String cid, String sid);

    /**删除选课记录*/
    int deleteElectionBySidCid(String cid, String sid);
}
