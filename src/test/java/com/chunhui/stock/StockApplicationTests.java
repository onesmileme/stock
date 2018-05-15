package com.chunhui.stock;

import com.chunhui.core.dto.InfoDao;
import com.chunhui.core.dto.StockBasicDto;
import com.chunhui.core.dto.StockBasicMapper;
import com.chunhui.core.dto.StockInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.chunhui.core.dto")
public class StockApplicationTests {

    @Autowired
    private StockBasicMapper basicMapper;

    @Autowired
    private StockInfoMapper infoMapper;

	@Test
	public void contextLoads() {
	}

	@Test
    public  void getAllBasicData(){

	    System.out.println("will test");
        List<StockBasicDto> allinfos = basicMapper.getAllBasicInfo();

        //*
        List<InfoDao> allInfos = infoMapper.getAllInfos();

        for (InfoDao dao : allInfos){
            System.out.println("name : "+dao);
        }

        System.out.println("all info count is: "+allInfos.size());
        //*/
    }

}
