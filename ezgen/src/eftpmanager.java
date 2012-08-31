import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elsoft.eftpmanager.EftpFileMng;

//주의 eftpmanager.java 이름을 다른이름.java로 변경사용시 public class 다른이름 extends HttpServlet
public class eftpmanager extends HttpServlet
{
	///Encoding을 위한 멤버 --------------------------------------------------------------------------
	public final static int IN_NONE = 0;
	public final static int IN_ENG_TO_KOR = 1;
	public final static int IN_KOR_TO_ENG = 2; //보통 DB NLS가 영문일때
	
	public final static int OUT_NONE = 0;
	public final static int OUT_TO_KOR = 1;
	public final static int OUT_ENG_TO_KOR = 2;	//보통 DB NLS가 영문일때
	
	static final String m_stEncoding = "KSC5601"; //Unicode를 Encoding 문자(KSC5601,MS949,...)로 변환합니다.
	static final int m_nInEncoding = IN_NONE;			//Input String 변환형식.
	static final int m_nOutEncoding = OUT_NONE;		//Output Stringf 변환 형식.
	
	//EftpFileMng 생성 합니다.(필수 사항) -----------------------------------------------------------
	private EftpFileMng m_eftpmng = new EftpFileMng();
	
	//HttpServlet 기본 함수로 Servlet POST 요청시 호출되는 함수입니다. ------------------------------
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException
	{
		response.setContentType("text/html;charset=euc-kr");
		
		m_eftpmng.doPost(request,response);	//EftpFileMng를 실행합니다.
	}
	
	//HttpServlet 기본 함수로 Servlet GET 요청시 호출되는 함수입니다. -------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException
	{
		response.setContentType("text/html;charset=euc-kr");

		m_eftpmng.getStatus(request,response);	//EftpFileMng의 현재 정보를 보여줍니다.
	}
	
	//Servlet 초기화시 호출되는 함수입니다. ---------------------------------------------------------
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		m_eftpmng.UseEncoding(m_nInEncoding,m_nOutEncoding,m_stEncoding);				//Encoding 문자를 사용할 경우 지정합니다.
		
		//m_eftpmng.setMaxThreads(int nMaxThreads,int nTimeOut);								//File Pool nMaxThreads:동시 접속 사용자 수, nTimeOut:제한시간(단위:초)
		
		//m_eftpmng.RootPathAndSeparator("<FullPath>","<DirectorySeparator>");	//<FullPath>는 마지막 디렉터리 구분자를 포함합니다.
		//m_eftpmng.RootPathAndSeparator("/ezgenhome/","/");										//Unix(Linux)의 디렉토리 구분자는 '/' 입니다.
		m_eftpmng.RootPathAndSeparator("C:\\Projects\\","\\");								//Windows의 디렉토리 구분자는 '\\' 입니다.
	}
	
	//Servlet 종료시 호출되는 함수로 작업 중인 모든 작업을 취소 합니다. -----------------------------
 	public void destroy()
	{
		super.destroy();
	}
	
}