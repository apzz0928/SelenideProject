import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.*;

import com.codeborne.selenide.testng.ScreenShooter;


public class gameMng_Selenide {
	private static String baseUrl, hubUrl;
	private static String TestBrowser;
	private static String startAt, endAt;
	private static String startAt1, endAt1;
	private static String startHour, endHour;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeTest(String browser) throws MalformedURLException {
		baseUrl = "https://rct-d-p.astorm.com";
		hubUrl = "http://10.10.105.228:4444/wd/hub";
  		
  		String urlToRemoteWD = hubUrl;
  		DesiredCapabilities cap;
  		ScreenShooter.captureSuccessfulTests = false;
  		if(browser.equals("chrome")){
  			TestBrowser = "chrome";
  			cap = DesiredCapabilities.chrome();
	        RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD),cap);
	        WebDriverRunner.setWebDriver(driver);
	  		driver.manage().window().setSize(new Dimension(1600, 1400));
  		} else if(browser.equals("firefox")) {
  			TestBrowser = "firefox";
  			cap = DesiredCapabilities.firefox();
	        RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD),cap);
	        WebDriverRunner.setWebDriver(driver);
	  		driver.manage().window().setSize(new Dimension(1600, 1400));
  		} else if(browser.equals("internetExplorer")) {
  			TestBrowser = "internetExplorer";
  			cap = DesiredCapabilities.internetExplorer();
  			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true); //IE 실행을 위한 보안 관련 설정 임시 변경
  			cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false); //ie text 입력 속도 향상을 위한 부분
  			cap.setCapability("requireWindowFocus", true); //ie text 입력 속도 향상을 위한 부분
  			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true); //ie 캐시 삭제를 위한 부분
	        RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD),cap);
	        WebDriverRunner.setWebDriver(driver);
	  		driver.manage().window().setSize(new Dimension(1600, 1400));
  		} else if(browser.equals("edge")) {
  			TestBrowser = "edge";
  			cap = DesiredCapabilities.edge();
	        RemoteWebDriver driver = new RemoteWebDriver(new URL(urlToRemoteWD),cap);
	        WebDriverRunner.setWebDriver(driver);
	  		driver.manage().window().setSize(new Dimension(1600, 1400));
  		}
        startAt = "2017.06.05 00:00:00";
        endAt = "2018.07.01 00:00:00";
        startAt1 = "2017-06-05 00:00:00";
        endAt1 = "2018-07-01 00:00:00";
        startHour = "07:00:00";
        endHour = "08:00:00";
    }
	private static void js(String javaScriptSource) {
	    executeJavaScript(javaScriptSource);
	}
  	public static void windowTitle(String windowTitle) { 
  		WebDriverRunner.getWebDriver();
  		Set<String> windows = getWebDriver().getWindowHandles();
  		for (String window : windows) { 
  			switchTo().window(window); 
  			if (getWebDriver().getTitle().contains(windowTitle)) { 
  				return; 
			} 
		} 
	}
  	public static void slang(String slang){
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(".ac_input", 0).setValue(TestBrowser + " Selenide 테스트 " + slang + " 정규표현식.");
		$(".ac_input", 2).setValue(TestBrowser + " Selenide 테스트 " + slang + " 코멘트.");
		$(".ac_input", 3).setValue(TestBrowser + " Selenide 테스트 " + slang + " 메세지");
		$(".uid_slang_word_add_test_btn").click();
		$(".ac_container_paragraph").waitUntil(text("일치하는 패턴이 존재하지 않습니다."), 1000);
		$(".uid_slang_word_add_btn").click();
		$(".uid_ok_btn").waitUntil(visible, 3000);
		$(".uid_ok_btn").click();
		$(".uid_ok_btn").waitUntil(visible, 3000);
		$(".uid_ok_btn").click();
		$$(".uid_slang_word_list_modify_btn").last().click();
		$(".ac_input", 6).setValue(TestBrowser + " ☆★☆★Selenide " + slang + " 테스트 정규표현식☆★☆★");
		$(".ac_input", 7).setValue(TestBrowser + " ☆★☆★Selenide " + slang + " 테스트 코멘트☆★☆★");
		$(".ac_input", 8).setValue(TestBrowser + " ☆★☆★Selenide " + slang + " 테스트 메세지☆★☆★");
		$(".uid_slang_word_modify_test_btn").click();
		$(".ac_container_paragraph").waitUntil(text("일치하는 패턴이 존재하지 않습니다."), 1000);
		$(".uid_slang_word_modify_btn").click();
		$(".uid_ok_btn").waitUntil(visible, 3000);
		$(".uid_ok_btn").click();
		$(".uid_ok_btn").waitUntil(visible, 3000);
		$(".uid_ok_btn").click();
		$$(".uid_slang_word_list_remove_btn").last().click();
		$(".uid_ok_btn").waitUntil(visible, 3000);
		$(".uid_ok_btn").click();
		$(".uid_ok_btn").waitUntil(visible, 3000);
		$(".uid_ok_btn").click();
  	}
	@Test(priority = 0)
	public void Login() {
        open(baseUrl + "/login/form.ct");
        $(By.linkText("LDAP")).click();
        $(By.name("j_username")).setValue("apzz0928");
        $(By.name("j_password")).setValue("qordlf!@34");
        $(".uid_ldap_login_submit_btn").click();
        open(baseUrl + "/common/locale/ko");
        System.out.println(TestBrowser + " Login : Pass");
    }
	//@Test(priority = 1)
	public void systemMessage() {
        open(baseUrl + "/gmcmd/systemMessageForm.ct");
        for(int i=0;i<4;){
        	$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
            $(".fa-circle-o", i).click();
            i++;
            $("input[name=holdingTimeSec]").setValue("" + i + "");
            $("input[name=displayMessage]").setValue(TestBrowser + " 위치" + i + ", 지속시간" + i + "초");
            $(".uid_gmcmd_call_btn").click();
            $(".uid_ok_btn").click();
        }
        $(".menu-title").waitUntil(text("전체 메뉴"), 3000);
        $(".fa-square-o", 0).click();
        $(".fa-circle-o", 0).click();
        $("input[name=holdingTimeSec]").setValue("5");
        $("input[name=displayMessage]").setValue(TestBrowser + " 롤링o, 위치1, 지속시간 5초");
        $(".uid_gmcmd_call_btn").click();
        $(".uid_ok_btn").click();
        $(".menu-title").waitUntil(text("전체 메뉴"), 3000);
        System.out.println(TestBrowser + " systemMessage : Pass");
    }
	//@Test(priority = 2)
	public void ingameNotice_add() {
		open(baseUrl + "/gmcmd/ingameNotice.ct");
		js("$('input[name=startAt]').val('" + startAt + "');");
		js("$('input[name=endAt]').val('" + endAt + "');");
        for (int i=0;i<7;i++ ){
            $(".fa-square-o", 0).click();        	
        }
        $("input[name=repeatTime]").setValue("1");
        $("input[name=name]").setValue(TestBrowser + " 공지 이름입니다.");
        $("input[name=desc]").setValue(TestBrowser + " 공지 설명입니다.");
        $(".fa-circle-o", 0).click();
        $(".ac_input", 11).setValue("10");
        $(".ac_input", 12).setValue("공지 내용 입니다.");
        $(".uid_setting_form_save_btn").click();
		$(".uid_ok_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		System.out.println(TestBrowser + " ingameNotice_add : Pass");
    }
	//@Test(priority = 3)
	public void ingameNotice_statusChange() {
		$(".uid_notice_start_btn").click();
		$(".uid_ok_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(".uid_notice_stop_btn").click();
		$(".uid_ok_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(".uid_notice_start_btn").click();
		$(".uid_ok_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(".uid_notice_end_btn").click();
		$(".uid_ok_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(".uid_history_show_btn", 0).click();
		$(".uid_layer_close").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		System.out.println(TestBrowser + " ingameNotice : Pass");
    }
	//@Test(priority = 4)
	public void semdMail() {
		open(baseUrl + "/gmcmd/sendMailForm.ct");
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$("input[name=recvAccountKey]").setValue("9000115");
		$("input[name=sendNickName]").setValue("Herowarz");
		$("input[name=mailTitle]").setValue(TestBrowser + " 메일 제목입니다.");
		$("textarea[name=mailContents]").setValue(TestBrowser + " 메일 내용입니다.");
		$(".uid_attach_item_search_btn").click();
		windowTitle("Control Tower @ Cockpit");
		$("#itemkeyword").setValue("마야의 목걸이 파편");
		$(".uid_attach_item_search_btn").click();
		if(TestBrowser.equals("chrome")){
			$(".btn").click();
		} 
		windowTitle("Control Tower @ reboot");
		$("input[name=itemCount]").setValue("1");
		$("input[name=gold]").setValue("10000");
		$("input[name=reason]").setValue(TestBrowser + " Seleniude Test 입니다.");
		$(".uid_gmcmd_sendmailcall_btn").click();
		$(".uid_ok_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(".fa-chevron-down", 1).click();
		$("li[data-key=admin]").click();
		$("input[name=keyword]").setValue("apzz0928");
		$(".uid_log_search_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		System.out.println(TestBrowser + " semdMail : Pass");
    }
	//@Test(priority = 5)
	public void kickUser() {
		open(baseUrl + "/gmcmd/kickUserForm.ct");
		$("input[name=accountKey]").setValue("9000115");
		$("input[name=forbidTime]").setValue("0");
		$("input[name=reason]").setValue(TestBrowser + " Selenide Test 입니다.");
		$(".uid_gmcmd_kickusercall_btn").click();
		$(".uid_ok_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		System.out.println(TestBrowser + " kickUser : Pass");
    }
	//@Test(priority = 6)
	public void mailout() {
		open(baseUrl + "/gmcmd/mailoutform.ct");
		$(By.name("filedata")).sendKeys("C:\\Users\\Administrator\\Downloads\\템골드전부미첨부.xlsx");
		$("input[name=reason]").setValue(TestBrowser + " Selenide Test 입니다.");
		$(".uid_mailout_submit_btn").click();
		$(".uid_ok_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(By.linkText("확인")).click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		System.out.println(TestBrowser + " mailout : Pass");
    }
	//@Test(priority = 7)
	public void burningEvent() {
		open(baseUrl + "/event/burning/list.ct");
		$(By.linkText("추가")).click();
		$(".fa-chevron-down", 1).click();
		$("li[data-key='0']").click();
//		$(".fa-chevron-down", 1).waitUntil(exist, 3000);
//		$(".fa-chevron-down", 1).click();
//		$("li[data-key='20']").click();
		$(".ac_input", 2).setValue(startAt1);
		$(".ac_input", 3).setValue(endAt1);
		$(".ac_btn_text", 2).click(); //등록
		$(".uid_ok_btn").click();
		$(".ac_btn_text", 2).click(); //동기화
		$(".uid_ok_btn").click();
		$(".uid_ok_btn").click();
		$(".ac_btn_text", 0).click(); //삭제
		$(".uid_ok_btn").click();
		$(".uid_ok_btn").click();
		$(".ac_btn_text", 1).click(); // 동기화
		$(".uid_ok_btn").click();
		$(".uid_ok_btn").click();
		$(By.linkText("종료")).click();
		$(".ac_btn_text", 0).click();
		$(".uid_ok_btn").click();
		$(".uid_ok_btn").click();
		System.out.println(TestBrowser + " burningEvent : Pass");
    }
	//@Test(priority = 8)
	public void serverStatus() {
		open(baseUrl + "/cache/getServerStatus.ct");
		$(".ac_input", 1).setValue("50");
		$(".fa-chevron-down", 1).click();
		$("li[data-key='true']").click();
		js("$('.ac_input').eq(3).val('2017.06.06 00:00:00');");
		js("$('.ac_input').eq(4).val('2018.07.07 00:00:00');");
		$(".fa-chevron-down", 2).click();
		$("li[data-key='enable']").click();
		$(".uid_server_status_all_btn").click();
		$(".ac_container_message_body").waitUntil(text("정상적으로 설정하였습니다."), 8000);
		$(".uid_ok_btn").click();
		$(".ac_input", 1).setValue("60");
		$(".fa-chevron-down", 1).click();
		$("li[data-key='false']").click();
		js("$('.ac_input').eq(3).val('2017.06.05 00:00:00');");
		js("$('.ac_input').eq(4).val('2018.07.08 00:00:00');");
		$(".fa-chevron-down", 2).click();
		$("li[data-key='disable']").click();
		$(".uid_server_status_all_btn").click();
		$(".ac_container_message_body").waitUntil(text("정상적으로 설정하였습니다."), 8000);
		$(".uid_ok_btn").click();
		$("input[name=keyword]").setValue("apzz0928");
		$(".uid_log_search_btn").click();
		System.out.println(TestBrowser + " serverStatus : Pass");
    }
	//@Test(priority = 9)
	public void limitDrop() {
		open(baseUrl + "/event/limitdrop/confUploadForm.ct");
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(By.name("filedata")).sendKeys("C:\\Users\\Administrator\\Downloads\\dropConfSample.xlsx");
		$(".ac_btn_text", 1).click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(".uid_state_change_btn").click();
		$(".uid_ok_btn").click();
		$(".uid_ok_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(".uid_reload_btn").click();
		$(".uid_ok_btn").click();		
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(".uid_state_change_btn").click();
		$(".uid_ok_btn").click();
		$(".uid_ok_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(".uid_reload_btn").click();
		$(".uid_ok_btn").click();
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		System.out.println(TestBrowser + " limitDrop : Pass");
    }
	//@Test(priority = 10)
	public void ingameGuide() {
		open(baseUrl + "/gmcmd/ingameGuide/list.ct");
		$(".uid_ingame_guide_add_btn").click();
		$("input[name=firstCategory]").setValue(TestBrowser + " Selenide 인게임 가이드 대분류");
		$("input[name=secondCategory]").setValue(TestBrowser + " Selenide 인게임 가이드 소분류");
		js("(function(){setTimeout(function(){$('.cke_wysiwyg_frame').contents().find('.cke_editable').text('내용 입력입니다.')}, 800);})();");
		$("input[name=order]").setValue("0");
        js("(function(){setTimeout(function(){$('.uid_ingame_guide_save_btn').click();}, 2000);})();");
		$(".ac_message_title").shouldHave(text("알림"));
		$(".uid_ok_btn").click();
		$(".ac_message_title").shouldHave(text("알림"));
		$(".uid_ok_btn").click();
		$(".uid_ingameguide_edit_btn", 0).click();
		$("input[name=firstCategory]").setValue(TestBrowser + " ☆★☆★Selenide 인게임 가이드 대분류☆★☆★");
		$("input[name=secondCategory]").setValue(TestBrowser + " ☆★☆★Selenide 인게임 가이드 소분류☆★☆★");
		$("input[name=order]").setValue("1");
		$(".uid_ingame_guide_save_btn").click();
		$(".uid_ok_btn").click();
		$(".uid_ok_btn").click();
		$(".uid_ingameguide_delete_btn").click();
		$(".uid_ok_btn").click();
		$(".uid_ok_btn").click();
		System.out.println(TestBrowser + " ingameGuide : Pass");
    }
	//@Test(priority = 11)
	public void onlineUser() {
		open(baseUrl + "/gmcmd/onlineUserCountInfo.ct");
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		System.out.println(TestBrowser + " onlineUser : Pass");
    }
	@Test(priority = 12)
	public void slangWord() {
		open(baseUrl + "/gmcmd/slangForm.ct");
		slang("명명 금칙어");
		System.out.println(TestBrowser + " slang type N : Pass");
		$(By.linkText("금칙어")).click();
		slang("금칙어");
		System.out.println(TestBrowser + " slang type B : Pass");
		$(By.linkText("대체어")).click();
		slang("대체어");
		System.out.println(TestBrowser + " slang type F : Pass");
		$(By.linkText("차단어")).click();
		slang("차단어");
		System.out.println(TestBrowser + " slang type I : Pass");
    }
	//@Test(priority = 13)
	public void pvpSchedule() throws InterruptedException {
		open(baseUrl + "/event/pvpSchedule/settingList.ct");
		$(".menu-title").waitUntil(text("전체 메뉴"), 3000);
		$(".uid_schedule_add_btn", 0).click();
		js("$('.ac_input').eq(6).val('" + startHour + "');");
		js("$('.ac_input').eq(7).val('" + endHour + "');");
		$(".ac_input", 8).setValue("2");
		$(".ac_input", 9).setValue("500001");
		$(".ac_input", 10).setValue("5");
		$(".uid_schedule_save_btn").scrollTo().shouldBe(appear).click();
		System.out.println(TestBrowser + " pvpSchedule add : Pass");
        $(".menu-title").waitUntil(text("전체 메뉴"), 10000);
        $(".uid_schedule_sync_btn").waitUntil(text("동기화"), 3000);
        $(".uid_schedule_sync_btn").scrollTo();
        $(".uid_schedule_save_btn").scrollTo();
		js("(function(){setTimeout(function(){document.querySelector('.uid_schedule_sync_btn').click();}, 500);})();");
        js("(function(){setTimeout(function(){document.querySelector('.uid_ok_btn').click();}, 700);})();");
        js("(function(){setTimeout(function(){document.querySelector('.uid_ok_btn').click();}, 1100);})();");
		$(".menu-title").waitUntil(text("전체 메뉴"), 10000);
		js("$('.ac_input').eq(6).val('" + startHour + "');");
		js("$('.ac_input').eq(7).val('" + endHour + "');");
		$(".ac_input", 8).setValue("3");
		$(".ac_input", 9).setValue("500002");
		$(".ac_input", 10).setValue("6");
		$(".uid_schedule_del_btn", 1).waitUntil(appear, 5000).click();
		$(".uid_schedule_save_btn").scrollTo().shouldBe(visible).click();
		System.out.println(TestBrowser + " pvpSchedule delete : Pass");
        $(".menu-title").waitUntil(text("전체 메뉴"), 10000);
        $(".uid_schedule_sync_btn").waitUntil(text("동기화"), 3000);
        $(".uid_schedule_sync_btn").scrollTo();
        $(".uid_schedule_save_btn").scrollTo();
        js("(function(){setTimeout(function(){document.querySelector('.uid_schedule_sync_btn').click();}, 800);})();");
        js("(function(){setTimeout(function(){document.querySelector('.uid_ok_btn').click();}, 1000);})();");
        js("(function(){setTimeout(function(){document.querySelector('.uid_ok_btn').click();}, 2000);})();");
		$(".menu-title").waitUntil(text("전체 메뉴"), 10000);
		System.out.println(TestBrowser + " pvpSchedule : Pass");
    }
	@AfterClass
	public void afterTest() {
		closeWebDriver();
	}
}
