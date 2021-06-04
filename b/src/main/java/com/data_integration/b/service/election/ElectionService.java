package com.data_integration.b.service.election;

import com.data_integration.b.pojo.election.Election;

import java.util.List;

public interface ElectionService {

    /**获取某学生所选择的课程信息*/
    List<Election> getElectionsBySid(String sid);

    /**删除选课记录*/
    int deleteElectionBySidCid(String cid, String sid);

    /**获取某门课程的选课信息*/
    List<Election> getElectionsByCid(String cid);

    /**获取所有的选课信息*/
    List<Election> getElections();

    /**更新课程成绩*/
    int updateGrade(String cid, String sid, int score);

    boolean addCourseSelecting(Election election) throws Exception;

    String addCourseSelectingXml(String content) throws Exception;
}
