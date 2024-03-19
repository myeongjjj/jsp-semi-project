package etc_p;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Servlet implementation class FileDown
 */
@WebServlet("/FileDown")
public class FileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDown() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String directory = request.getServletContext().getRealPath("fff/");
		directory = "C:\\kimhyejung\\study\\javaWork\\first-Proj-Team\\firstProj\\src\\main\\webapp\\fff\\";
		
		String fileName = request.getParameter("fName");
		if (fileName != null && !fileName.isEmpty()) {
		    response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
		}
		System.out.println(fileName);
		
		ServletOutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(directory+fileName);
		
		byte [] buffer = new byte[1024];
		//int len = in.read(buffer);
		/*
		 * while(len!=-1) { out.write(buffer, 0, len); }
		 */
		while(in.available()>0) {
			int len = in.read(buffer);
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
