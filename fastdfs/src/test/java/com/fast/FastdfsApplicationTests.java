package com.fast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastdfsApplicationTests {

	@Autowired
	private JavaMailSender javaMailSender;

	@Test
	public void contextLoads() {
		String str = "img/anc.jpg";
		String substring = str.substring(str.lastIndexOf(".") + 1);
		System.out.println(substring);
	}

}
