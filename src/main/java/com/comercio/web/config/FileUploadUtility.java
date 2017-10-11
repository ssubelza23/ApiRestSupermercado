package com.comercio.web.config;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import org.slf4j.*;

public class FileUploadUtility {
	private static final String ABS_PATH="C:\\Tomcat 9.0\\webapps\\web\\src\\main\\resources\\static\\assets\\images\\usuarios\\";

	private static String REAL_PATH="";

	private static final Logger logger=LoggerFactory.getLogger(FileUploadUtility.class);
	public static void uploadFile(HttpServletRequest request, MultipartFile foto,String dni) {
		
		//ger the real path
		
		REAL_PATH=request.getSession().getServletContext().getRealPath("/assets/images/");
		
		logger.info(REAL_PATH);
		
		if(!new File(ABS_PATH).exists()){
			//crear el direcorio
			new File(ABS_PATH).mkdirs();
			
		}
		
		if(!new File(REAL_PATH).exists()){
			//crear el direcorio
			new File(REAL_PATH).mkdirs();
			
		}
		
		try{
			//subir al servidor
			//foto.transferTo(new File(REAL_PATH +dni+".jpg"));
			//cargar al proyecto del directorio
			foto.transferTo(new File(ABS_PATH +dni+".jpg"));
		}
		catch(IOException ex){
			
		}
	}
	
	
}
