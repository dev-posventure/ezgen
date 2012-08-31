import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//주의 hnwhtml.java 이름을 다른이름.java로 변경사용시 public class 다른이름 extends HttpServlet
public class hnwhtml extends HttpServlet
{
	static final String classid = "clsid:2E68BEE5-A640-11D2-AEA4-00AA006E5B34";
	static final String version = "7,1,0,20";
	//static final String codebasepath = "/hnwocx/hnwactiv_x_x_x_x.cab";
	//static final String stinitdns = "[DNSVariable1]=value1[DNSVariable2]=value2..."; //DNS변수 사용시
	static final String codebase = "http://localhost:8080/ezgen/hnwocx/hnwactiv_7_1_0_20.cab";
	static final String stJavaScriptSrc = "http://localhost:8080/ezgen/js/ezgencontrol.js";
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		this.printHTML(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.printHTML(request, response);
	}
	
	public void printHTML(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=euc-kr");
		
		PrintWriter out = response.getWriter();
		
		/*
		String codebase = "http://";
		String stServerName = request.getServerName();
		String stPort = Integer.toString(request.getServerPort());
		codebase += stServerName;
		if( stPort != null )
		{
			codebase += ":";
			codebase += stPort;
		}
		codebase += codebasepath;
		*/
		
		String bg_color = new String("FFFFFF");
		String userbgcol = request.getParameter("bgcolor");
		String sthnwsrc = request.getParameter("hnwsrc");
		if( sthnwsrc == null ) sthnwsrc = new String("");
		//else sthnwsrc = new String(sthnwsrc.getBytes("8859_1"),"KSC5601");
		String stprojectsrc = request.getParameter("projectsrc");
		if( stprojectsrc == null ) stprojectsrc = new String("");
		String stinitvalue = request.getParameter("initvalue");
		if( stinitvalue == null ) stinitvalue = new String("");
		//else stinitvalue = new String(stinitvalue.getBytes("8859_1"),"KSC5601");
		String stcheckproj = request.getParameter("checkproj");
		if( stcheckproj == null ) stcheckproj = new String("");
		String stlanguage = request.getParameter("language");
		if( stlanguage == null ) stlanguage = new String("");
		
		
		if( userbgcol != null ) bg_color = userbgcol;
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=Content-Type content=\"text/html; charset=euc-kr\">");
		out.println("<script language=\"JavaScript\" src=\""+stJavaScriptSrc+"\"> </script>");
		out.println("<title>EZgen</title>");
		out.println("</head>\n");
		out.println("<body topmargin=\"0\" leftmargin=\"0\" bgcolor="+bg_color+">\n");
		out.println("<comment id=\"__EZGENELEMENT_ID__\">");
		out.println("<object classid="+classid+" width=\"3\" height=\"40\"");
		out.println("codebase="+codebase+"#version="+version+">");
		if( stprojectsrc.length() > 0 )
		{
			out.println("<param name=\"projectsrc\" VALUE=\""+stprojectsrc+"\">");
		}
		out.println("<param name=\"hnwsrc\" VALUE=\""+sthnwsrc+"\">");
		out.println("<param name=\"initvalue\" VALUE=\""+stinitvalue+"\">");
		//out.println("<param name=\"initdns\" VALUE=\""+stinitdns+"\">\n"); //DNS변수 사용시
		if( stlanguage.length() > 0 )
		{
			out.println("<param name=\"language\" VALUE=\""+stlanguage+"\">");
		}
		if( stcheckproj.length() > 0 )
		{
			out.println("<param name=\"checkproj\" VALUE=\""+stcheckproj+"\">");
		}
		out.println("</object>");
		out.println("</comment>");
		out.println("<script>WriteEZgenElement(__EZGENELEMENT_ID__);</script>\n");
		out.println("</body>");
		out.println("</html>");
		
  }
}
