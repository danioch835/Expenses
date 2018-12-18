package expenses.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImagesController {
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("file")
	//@ResponseBody
	public void getFile(HttpServletResponse response) throws IOException {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<100;i++) {
			sb.append(i);
			sb.append(System.lineSeparator());
			sb.append("/");
			sb.append(System.lineSeparator());
		}
		String fileName = "file" + System.currentTimeMillis() + ".txt";
		response.setHeader("Content-Type", "text/plain;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] bytesArray = sb.toString().getBytes();
		
		sos.write(bytesArray, 0, bytesArray.length);
		sos.flush();
		sos.close();
		//return sb.toString();
	}
	
	/*@RequestMapping(value="/images/{nazwa}")
	public void getImage(HttpServletResponse response, 
						 Model uiModel,
						 @PathVariable(name="nazwa") String nazwa) throws IOException  {
//		InputStream in = context.getResourceAsStream("/WEB-INF/images/image.png");
		
		File fnew=new File("C:/Users/Daniel/Desktop/" + nazwa + ".png");
		BufferedImage originalImage=ImageIO.read(fnew);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(originalImage, "png", baos );
		byte[] imageInByte=baos.toByteArray();

		
		int width = originalImage.getWidth();
	    int height = originalImage.getHeight();
		
	    response.setContentType(MediaType.IMAGE_PNG_VALUE);

	    BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageInByte));
	    BufferedImage image= new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    image.getGraphics().drawImage(img, 0, 0, null);
	    
	    width = img.getWidth();
	    height = img.getHeight();

	    // separate colors
	    for (int col = 0; col < width; col++) {
	        for (int row = 0; row < height; row++) {
	        	Color c = new Color(img.getRGB(col, row), true);
	        	int a = c.getAlpha();
	        	int r = c.getRed();
	        	int g = c.getGreen();
	        	int b = c.getBlue();
	        	image.setRGB(col, row, new Color(255, 255, 255, a).getRGB());
	        	Color kolor = new Color(img.getRGB(col, row), true);
	        	a=kolor.getAlpha();
	        	r=kolor.getRed();
	        	g=kolor.getGreen();
	        	b=kolor.getBlue();
	        }
	    }
	    
	    img = image;
	    
	    baos=new ByteArrayOutputStream();
		ImageIO.write(img, "png", baos );
		imageInByte=baos.toByteArray();
	    
	    ServletOutputStream responseOutputStream = response.getOutputStream();
	    responseOutputStream.write(imageInByte);
	    responseOutputStream.flush();
	    responseOutputStream.close();
	    
//	    IOUtils.copy(imageInByte, response.getOutputStream());
	}*/
}
