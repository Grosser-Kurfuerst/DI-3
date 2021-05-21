package com.data_integration.b.dao.election;

import com.data_integration.b.pojo.election.Election;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ElectionDao {

    List<Election> getElectionsBySid(String sid);

    int addElectionBySidCid(String cid, String sid);

    int deleteElectionBySidCid(String cid, String sid);

    Election getElectionByCidSid(String cid, String sid);

    List<Election> getElectionsByCid(String cid);

    List<Election> getElections();

    int updateGrade(String cid, String sid, int score);
}
