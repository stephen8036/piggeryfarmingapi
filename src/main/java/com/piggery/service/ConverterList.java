package com.piggery.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piggery.repo.PigsRepository;


@Service
public class ConverterList {
	
	
		public List<Double> convertStringToList(Double num, String listString, Integer number, String listString2){
			List<Double> converted = Stream.of(listString.split(",")).map(String::trim).map(Double::parseDouble).collect(Collectors.toList());
			if(number == 0) {
				 converted.add(num);
				 return converted;
			}else {
				List<Double> converted2 = Stream.of(listString2.split(",")).map(String::trim).map(Double::parseDouble).collect(Collectors.toList());
				 Double lastElement =converted.get(converted.size() - 1);
				 Double difference =(double)Math.round((num-lastElement)* 100)/100;
				 converted2.add(difference);
				 return converted2;
			}
			
		}
		
		public String convertListToString(List<Double> list){
			String converted = list.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(","));
			return converted;
		}
		
		
		
	
	

}
