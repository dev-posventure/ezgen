import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elsoft.jdbc.elsjdbc;

//주의 hnwjdbc.java 이름을 다른이름.java로 변경사용시 public class 다른이름 extends HttpServlet
public class hnwjdbc extends HttpServlet
{
	//DBMS와 접속을 위한 고정 변수 elsjdbc의 Connect Pooling을 위한 필수 멤버 입니다.
	//아래의 문자 값은 DBMS마다 조금식 차이가 있습니다.
	
	//Connect Pooling을 위한 멤버 -------------------------------------------------------------------
	//JDBC-ODBC Bridge ------------------------------------------------------------------------------
	//static final String m_stDriverClass = "sun.jdbc.odbc.JdbcOdbcDriver";
	//static final String m_stURL = "jdbc:odbc:<DataSourceName>";
	//static final String m_stUID = "";
	//static final String m_stPWD = "";
	//-----------------------------------------------------------------------------------------------
	//Oracle-thin[ojdbc14.jar] ----------------------------------------------------------------------
	//static final String m_stDriverClass = "oracle.jdbc.driver.OracleDriver";
	//static final String m_stURL = "jdbc:oracle:thin:@<host>:<port1521>:<sid>";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//Oracle-oci[ojdbc14.jar] -----------------------------------------------------------------------
	//static final String m_stDriverClass = "oracle.jdbc.driver.OracleDriver";
	//static final String m_stURL = "jdbc:oracle:oci:@<host>:<port1521>:<sid>";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
//	DB2 UDB[db2jcc.jar, db2jcc_license_cu.jar] ----------------------------------------------------
	static final String m_stDriverClass = "com.ibm.db2.jcc.DB2Driver";
	static final String m_stURL = "jdbc:db2://192.168.20.12:61000/CABIS";
	static final String m_stUID = "jcbsc";
	static final String m_stPWD = "jcbsc";
	//-----------------------------------------------------------------------------------------------
	//MS-SQLSERVER[mssqlserver.jar, msbase.jar, msutil.jar] -----------------------------------------
	//static final String m_stDriverClass = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	//static final String m_stURL = "jdbc:microsoft.sqlserver://<host>:<port1433>;DatabaseName=<database>";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//Sybase ASE[jconn2.jar] ------------------------------------------------------------------------
	//static final String m_stDriverClass = "com.sybase.jdbc2.jdbc.SybDriver";
	//static final String m_stURL = "jdbc.sybase.Tds:<host>:<port2048>/<database>";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//Sybase ASA[jconn2.jar] ------------------------------------------------------------------------
	//static final String m_stDriverClass = "com.sybase.jdbc2.jdbc.SybDriver";
	//static final String m_stURL = "jdbc.sybase:Tds:<host>:<port2638>/<database>";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//Infomix[ifxjdbc.jar] --------------------------------------------------------------------------
	//static final String m_stDriverClass = "com.informix.jdbc.IfxDriver";
	//static final String m_stURL = "jdbc:informix-sqli://<host>:<port>/<database>:INFORMIXSERVER=<dbservername>";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//MySQL[mysql-connector-java-3.1.13-bin.jar] ----------------------------------------------------
	//static final String m_stDriverClass = "com.mysql.jdbc.Driver";
	//static final String m_stURL = "jdbc.mysql://<host>:<port3306>/<database>";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//PostgreSQL[pg74.215.jdbc2ee.jar] --------------------------------------------------------------
	//static final String m_stDriverClass = "org.postgresql.Driver";
	//static final String m_stURL = "jdbc.postgresql://<host>:<port5432>/<database>";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//Lotus Notes -----------------------------------------------------------------------------------
	//static final String m_stDriverClass = "lotus.jdbc.domino.DominoDriver";
	//static final String m_stURL = "jdbc:domino:/employee.nsf/skywalk/skc"; 
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//Progress --------------------------------------------------------------------------------------
	//static final String m_stDriverClass = "com.progress.sql.jdbc.JdbcProgressDriver";
	//static final String m_stURL = "jdbc:JdbcProgress:T:<host>:<port>:<database>"; 
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//MaxDB[sapjdbc.jar] ----------------------------------------------------------------------------
	//static final String m_stDriverClass = "com.sap.dbtech.jdbc.DriverSapDB";
	//static final String m_stURL = "jdbc:sapdb://<host>:<port>/<database>";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//FrontBase[frontbasejdbc.jar] ------------------------------------------------------------------
	//static final String m_stDriverClass = "com.frontbase.jdbc.FBJDriver";
	//static final String m_stURL = "jdbc:FrontBase://<host>:<port>/<database>";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//HSQL[hsqjdb.jar] ------------------------------------------------------------------------------
	//static final String m_stDriverClass = "org.hsqldb.jdbcDriver";
	//static final String m_stURL = "jdbc:hsqldb:<database>";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	//jTDS[jtds.jar] --------------------------------------------------------------------------------
	//static final String m_stDriverClass = "net.sourceforge.jtds.jdbc.Driver";
	//static final String m_stURL = "jdbc:jtds:<server_type>://<server>[:<port>][/<database>]";
	//static final String m_stUID = "UID";
	//static final String m_stPWD = "PWD";
	//-----------------------------------------------------------------------------------------------
	static final int m_nMaxThreads = 10;	//최대 동시 접속 수를 지정합니다.
	static final int m_nTimeOut = 60;			//최대 동시 접속 수를 초가시 대기시간(초)

	//Encoding을 위한 멤버 --------------------------------------------------------------------------
	public final static int IN_NONE = 0;
	public final static int IN_ENG_TO_KOR = 1;
	public final static int IN_KOR_TO_ENG = 2;	//보통 DB NLS가 영문일때

	public final static int OUT_NONE = 0;
	public final static int OUT_TO_KOR = 1;
	public final static int OUT_ENG_TO_KOR = 2;	//보통 DB NLS가 영문일때

	static final String m_stEncoding = "KSC5601";	//Unicode를 Encoding 문자(KSC5601,MS949,...)로 변환합니다.
	static final int m_nInEncoding = IN_NONE;			//Input String 변환형식.
	static final int m_nOutEncoding = OUT_NONE;		//Output Stringf 변환 형식.
	static final boolean m_bTrim = false;					//문자 필드 Trim 사용 여부.
	
	//Log을 위한 멤버 -------------------------------------------------------------------------------
	//static final int m_nLogType = 2;		//0:하지않음,1:System.out,2:사용자 파일
	//static final String m_stLogFile = "c:\\temp\\ezgen.log";
	//static final int m_nConLogType = 2;	//0:하지않음,1:System.out,2:사용자 파일
	//static final String m_stConLogFile = "c:\\temp\\ezgencon.log";
	
	//Pool을 위한 멤버 ------------------------------------------------------------------------------
	private int m_nMonitorInterval = 600;	//600초(10분) 주기 마다 쓰레드가 돌면서 Connection 의 상태를 검점합니다.
	private int m_nPoolTimeout = 3000; 		//3000초(50분) 동안 놀고 있는 커넥션이 있다면 reap 쓰레드가 제거합니다.
	
	//elsjdbc를 생성 합니다.(필수 사항) -------------------------------------------------------------
	private elsjdbc m_HnwQuery = new elsjdbc(m_nMonitorInterval,m_nPoolTimeout);
	
	//HttpServlet 기본 함수로 Servlet POST 요청시 호출되는 함수입니다. ------------------------------
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException
	{
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html;charset=euc-kr");
		
		m_HnwQuery.RunQuery(request,response);	//elsjdbc Connect Pooling을 사용하여 쿼리를 실행합니다.
	}
	
	//HttpServlet 기본 함수로 Servlet GET 요청시 호출되는 함수입니다. -------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException
	{
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html;charset=euc-kr");
		
		m_HnwQuery.getStatus(request,response);	//Connect Pooling에 대한 현재 정보(elsjdbc의 현재 정보)를 모니터링합니다. 
	}
	
	//Servlet 초기화시 호출되는 함수입니다. ---------------------------------------------------------
	//elsjdbc의 Connect Pooling을 위한 필수 함수로 웹서버 자체 Connect Pooling을 사용할 경우 아래 함수를 삭제 하십시오.
	//------>
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		m_HnwQuery.init(m_stDriverClass,m_nMaxThreads,m_nTimeOut,m_stURL,m_stUID,m_stPWD);
		m_HnwQuery.UseEncoding(m_nInEncoding,m_nOutEncoding,m_stEncoding,m_bTrim);	//Encoding 문자를 사용할 경우 지정합니다.
		
		//SQL문 문자 대치------------------------------------------------------------------------------
		//m_HnwQuery.SQLReplace("nsocom.euimuja","$oma37.comdat.euimuja",true);
		//SQLReplace(String stOld, String stNew, boolean bIgnoreCase);
		//String stOld : 대치될 문자
		//String stNew : 대치 문자
		//boolean bIgnoreCase : stOld 검색 시 대소문자 구분하지 않음(true),구분함(false)
		
		//Log 파일 생성 -------------------------------------------------------------------------------
		//개발 단계에서 Debug목적으로 사용하며 운영시 사용하면 속도가 늦어질 수 있습니다.
		//m_HnwQuery.setLogFile(m_nLogType,m_stLogFile);
		//m_HnwQuery.setLogConnect(m_nConLogType,m_stConLogFile);

		//DNS 필터 보안 -------------------------------------------------------------------------------
		//필터 추가시 도메인과 IP를 모두 추가하여 사용 하십시오.
		//m_HnwQuery.addDNSFilter("127.0.0.1:8080");
		//m_HnwQuery.addDNSFilter("www.ezgen.co.kr");
	}
	
	//Servlet 종료시 호출되는 함수로 Connect된 모든 Pooling를 닫습니다. -----------------------------
 	public void destroy()
	{
		super.destroy();
		m_HnwQuery.release();
	}

}