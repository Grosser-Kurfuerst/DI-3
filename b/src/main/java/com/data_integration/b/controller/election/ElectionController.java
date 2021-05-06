package com.data_integration.b.controller.election;

import com.data_integration.b.pojo.election.Election;
import com.data_integration.b.service.election.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/election")
public class ElectionController {


    @Autowired
    ElectionService electionService;

    /**
     * 获取某学生所选择的课程信息
     */
    @GetMapping("/getElections/{sid}")
    public List<Election> getElectionsBySid(@PathVariable String sid) {
        return electionService.getElectionsBySid(sid);
    }


    /**
     * 添加学生的选课
     * @param cid 长度小于等于5
     * @param sid 长度小于等于9
     * @return sid所选的课程
     */
    @GetMapping("/elect/{cid}/{sid}")
    public List<Election> addElectionByCidSid (@PathVariable String cid, @PathVariable String sid) {
        electionService.addElectionBySidCid(cid, sid);
        return getElectionsBySid(sid);
    }


    /**
     * 删除学生的选课
     */
    @GetMapping("delete/{cid}/{sid}")
    public List<Election> deleteElectionBySidCid(@PathVariable String cid, @PathVariable String sid) {
        electionService.deleteElectionBySidCid(cid, sid);
        return getElectionsBySid(sid);
    }
}
