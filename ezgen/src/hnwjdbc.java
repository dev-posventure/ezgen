import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elsoft.jdbc.elsjdbc;

//���� hnwjdbc.java �̸��� �ٸ��̸�.java�� ������� public class �ٸ��̸� extends HttpServlet
public class hnwjdbc extends HttpServlet
{
	//DBMS�� ������ ���� ���� ���� elsjdbc�� Connect Pooling�� ���� �ʼ� ��� �Դϴ�.
	//�Ʒ��� ���� ���� DBMS���� ���ݽ� ���̰� �ֽ��ϴ�.
	
	//Connect Pooling�� ���� ��� -------------------------------------------------------------------
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
	static final int m_nMaxThreads = 10;	//�ִ� ���� ���� ���� �����մϴ�.
	static final int m_nTimeOut = 60;			//�ִ� ���� ���� ���� �ʰ��� ���ð�(��)

	//Encoding�� ���� ��� --------------------------------------------------------------------------
	public final static int IN_NONE = 0;
	public final static int IN_ENG_TO_KOR = 1;
	public final static int IN_KOR_TO_ENG = 2;	//���� DB NLS�� �����϶�

	public final static int OUT_NONE = 0;
	public final static int OUT_TO_KOR = 1;
	public final static int OUT_ENG_TO_KOR = 2;	//���� DB NLS�� �����϶�

	static final String m_stEncoding = "KSC5601";	//Unicode�� Encoding ����(KSC5601,MS949,...)�� ��ȯ�մϴ�.
	static final int m_nInEncoding = IN_NONE;			//Input String ��ȯ����.
	static final int m_nOutEncoding = OUT_NONE;		//Output Stringf ��ȯ ����.
	static final boolean m_bTrim = false;					//���� �ʵ� Trim ��� ����.
	
	//Log�� ���� ��� -------------------------------------------------------------------------------
	//static final int m_nLogType = 2;		//0:��������,1:System.out,2:����� ����
	//static final String m_stLogFile = "c:\\temp\\ezgen.log";
	//static final int m_nConLogType = 2;	//0:��������,1:System.out,2:����� ����
	//static final String m_stConLogFile = "c:\\temp\\ezgencon.log";
	
	//Pool�� ���� ��� ------------------------------------------------------------------------------
	private int m_nMonitorInterval = 600;	//600��(10��) �ֱ� ���� �����尡 ���鼭 Connection �� ���¸� �����մϴ�.
	private int m_nPoolTimeout = 3000; 		//3000��(50��) ���� ��� �ִ� Ŀ�ؼ��� �ִٸ� reap �����尡 �����մϴ�.
	
	//elsjdbc�� ���� �մϴ�.(�ʼ� ����) -------------------------------------------------------------
	private elsjdbc m_HnwQuery = new elsjdbc(m_nMonitorInterval,m_nPoolTimeout);
	
	//HttpServlet �⺻ �Լ��� Servlet POST ��û�� ȣ��Ǵ� �Լ��Դϴ�. ------------------------------
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException
	{
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html;charset=euc-kr");
		
		m_HnwQuery.RunQuery(request,response);	//elsjdbc Connect Pooling�� ����Ͽ� ������ �����մϴ�.
	}
	
	//HttpServlet �⺻ �Լ��� Servlet GET ��û�� ȣ��Ǵ� �Լ��Դϴ�. -------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException
	{
		request.setCharacterEncoding("euc-kr");
		response.setContentType("text/html;charset=euc-kr");
		
		m_HnwQuery.getStatus(request,response);	//Connect Pooling�� ���� ���� ����(elsjdbc�� ���� ����)�� ����͸��մϴ�. 
	}
	
	//Servlet �ʱ�ȭ�� ȣ��Ǵ� �Լ��Դϴ�. ---------------------------------------------------------
	//elsjdbc�� Connect Pooling�� ���� �ʼ� �Լ��� ������ ��ü Connect Pooling�� ����� ��� �Ʒ� �Լ��� ���� �Ͻʽÿ�.
	//------>
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		m_HnwQuery.init(m_stDriverClass,m_nMaxThreads,m_nTimeOut,m_stURL,m_stUID,m_stPWD);
		m_HnwQuery.UseEncoding(m_nInEncoding,m_nOutEncoding,m_stEncoding,m_bTrim);	//Encoding ���ڸ� ����� ��� �����մϴ�.
		
		//SQL�� ���� ��ġ------------------------------------------------------------------------------
		//m_HnwQuery.SQLReplace("nsocom.euimuja","$oma37.comdat.euimuja",true);
		//SQLReplace(String stOld, String stNew, boolean bIgnoreCase);
		//String stOld : ��ġ�� ����
		//String stNew : ��ġ ����
		//boolean bIgnoreCase : stOld �˻� �� ��ҹ��� �������� ����(true),������(false)
		
		//Log ���� ���� -------------------------------------------------------------------------------
		//���� �ܰ迡�� Debug�������� ����ϸ� ��� ����ϸ� �ӵ��� �ʾ��� �� �ֽ��ϴ�.
		//m_HnwQuery.setLogFile(m_nLogType,m_stLogFile);
		//m_HnwQuery.setLogConnect(m_nConLogType,m_stConLogFile);

		//DNS ���� ���� -------------------------------------------------------------------------------
		//���� �߰��� �����ΰ� IP�� ��� �߰��Ͽ� ��� �Ͻʽÿ�.
		//m_HnwQuery.addDNSFilter("127.0.0.1:8080");
		//m_HnwQuery.addDNSFilter("www.ezgen.co.kr");
	}
	
	//Servlet ����� ȣ��Ǵ� �Լ��� Connect�� ��� Pooling�� �ݽ��ϴ�. -----------------------------
 	public void destroy()
	{
		super.destroy();
		m_HnwQuery.release();
	}

}