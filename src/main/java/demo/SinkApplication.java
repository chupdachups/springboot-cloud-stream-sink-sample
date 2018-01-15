/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Headers;

@SpringBootApplication
@EnableBinding(Sink.class)
public class SinkApplication {
	
	protected Logger logger = Logger.getLogger(SinkApplication.class.getName());

	
	public static void main(String[] args) {
		SpringApplication.run(SinkApplication.class, args);
	}
	
	final static String queueName = "test-queue";
	
	@RabbitListener(queues=queueName)
	public void sinkApi_A(String msg, @Headers Map hMap) {
		logger.info("=================== Sink ==================" );
		logger.info("Message >>>>>>>>>>>>>>>>>>>>  "+msg);
		for( Object key : hMap.keySet() ){
				System.out.println( String.format("키 : %s, 값 : %s", key, hMap.get(key)) );
		}
	}
	
	@StreamListener(Sink.INPUT)
	public void sinkApi_B(String msg, @Headers Map hMap) {
		logger.info("=================== Sink ==================" );
		logger.info("Message >>>>>>>>>>>>>>>>>>>>  "+msg);
		for( Object key : hMap.keySet() ){
				System.out.println( String.format("키 : %s, 값 : %s", key, hMap.get(key)) );
		}
	}
	

}
