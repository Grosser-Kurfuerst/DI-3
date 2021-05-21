package com.data_integration.b.serviceImpl.election;


import com.data_integration.b.dao.election.ElectionDao;
import com.data_integration.b.pojo.election.Election;
import com.data_integration.b.service.election.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectionServiceImpl implements ElectionService {

    @Autowired
    ElectionDao electionDao;


    /**
     * 获取某学生所选择的课程信息
     */
    @Override
    public List<Election> getElectionsBySid(String sid) {
        return electionDao.getElectionsBySid(sid);
    }


    /**
     * 添加选课记录
     */
    @Override
    public int addElectionBySidCid(String cid, String sid) {
        Election election = electionDao.getElectionByCidSid(cid, sid);
        if (election == null) return electionDao.addElectionBySidCid(cid, sid);
        else return 0;
    }

    /**
     * 删除选课记录
     */
    @Override
    public int deleteElectionBySidCid(String cid, String sid) {
        return electionDao.deleteElectionBySidCid(cid, sid);
    }


    /**
     * 获取某门课程的选课信息
     * @param cid 课程编号
     */
    @Override
    public List<Election> getElectionsByCid(String cid) {
        return electionDao.getElectionsByCid(cid);
    }

    /**
     * 获取所有的选课信息
     */
    @Override
    public List<Election> getElections() {
        return electionDao.getElections();
    }


    /**
     * 更新课程成绩
     */
    @Override
    public int updateGrade(String cid, String sid, int score) {
        return electionDao.updateGrade(cid, sid, score);
    }
}
