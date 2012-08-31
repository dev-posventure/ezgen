import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elsoft.eftpmanager.EftpFileMng;

//���� eftpmanager.java �̸��� �ٸ��̸�.java�� ������� public class �ٸ��̸� extends HttpServlet
public class eftpmanager extends HttpServlet
{
	///Encoding�� ���� ��� --------------------------------------------------------------------------
	public final static int IN_NONE = 0;
	public final static int IN_ENG_TO_KOR = 1;
	public final static int IN_KOR_TO_ENG = 2; //���� DB NLS�� �����϶�
	
	public final static int OUT_NONE = 0;
	public final static int OUT_TO_KOR = 1;
	public final static int OUT_ENG_TO_KOR = 2;	//���� DB NLS�� �����϶�
	
	static final String m_stEncoding = "KSC5601"; //Unicode�� Encoding ����(KSC5601,MS949,...)�� ��ȯ�մϴ�.
	static final int m_nInEncoding = IN_NONE;			//Input String ��ȯ����.
	static final int m_nOutEncoding = OUT_NONE;		//Output Stringf ��ȯ ����.
	
	//EftpFileMng ���� �մϴ�.(�ʼ� ����) -----------------------------------------------------------
	private EftpFileMng m_eftpmng = new EftpFileMng();
	
	//HttpServlet �⺻ �Լ��� Servlet POST ��û�� ȣ��Ǵ� �Լ��Դϴ�. ------------------------------
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException
	{
		response.setContentType("text/html;charset=euc-kr");
		
		m_eftpmng.doPost(request,response);	//EftpFileMng�� �����մϴ�.
	}
	
	//HttpServlet �⺻ �Լ��� Servlet GET ��û�� ȣ��Ǵ� �Լ��Դϴ�. -------------------------------
	public void doGet(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException
	{
		response.setContentType("text/html;charset=euc-kr");

		m_eftpmng.getStatus(request,response);	//EftpFileMng�� ���� ������ �����ݴϴ�.
	}
	
	//Servlet �ʱ�ȭ�� ȣ��Ǵ� �Լ��Դϴ�. ---------------------------------------------------------
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		
		m_eftpmng.UseEncoding(m_nInEncoding,m_nOutEncoding,m_stEncoding);				//Encoding ���ڸ� ����� ��� �����մϴ�.
		
		//m_eftpmng.setMaxThreads(int nMaxThreads,int nTimeOut);								//File Pool nMaxThreads:���� ���� ����� ��, nTimeOut:���ѽð�(����:��)
		
		//m_eftpmng.RootPathAndSeparator("<FullPath>","<DirectorySeparator>");	//<FullPath>�� ������ ���͸� �����ڸ� �����մϴ�.
		//m_eftpmng.RootPathAndSeparator("/ezgenhome/","/");										//Unix(Linux)�� ���丮 �����ڴ� '/' �Դϴ�.
		m_eftpmng.RootPathAndSeparator("C:\\Projects\\","\\");								//Windows�� ���丮 �����ڴ� '\\' �Դϴ�.
	}
	
	//Servlet ����� ȣ��Ǵ� �Լ��� �۾� ���� ��� �۾��� ��� �մϴ�. -----------------------------
 	public void destroy()
	{
		super.destroy();
	}
	
}