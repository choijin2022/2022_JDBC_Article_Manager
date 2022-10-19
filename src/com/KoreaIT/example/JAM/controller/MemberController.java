package com.KoreaIT.example.JAM.controller;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.service.MemberService;

public class MemberController extends Controller {

	private MemberService memberService;
	//로그인 접속 정보를 Member로 저장하는게 맞는지?
	Member member;
	public MemberController() {
		this.memberService = Container.memberService;
	}

	public void doJoin(String cmd) {
		String loginId = null;
		String loginPw = null;
		String loginPwChk = null;
		String name = null;
		System.out.println("== 회원가입 ==");

		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요");
				continue;
			}

			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

			if (isLoginIdDup) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}

			break;
		}
		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요");
				continue;
			}

			boolean loginPwCheck = true;

			while (true) {
				System.out.printf("비밀번호 확인 : ");
				loginPwChk = sc.nextLine().trim();

				if (loginPwChk.length() == 0) {
					System.out.println("비밀번호 확인을 입력해주세요");
					continue;
				}

				if (loginPw.equals(loginPwChk) == false) {
					System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
					loginPwCheck = false;
				}
				break;
			}
			if (loginPwCheck) {
				break;
			}
		}

		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();

			if (name.length() == 0) {
				System.out.println("이름을 입력해주세요");
				continue;
			}
			break;
		}

		memberService.doJoin(loginId, loginPw, name);

		System.out.printf("%s 님, 가입 되었습니다\n", name);
	}

	public void doLogin(String cmd) {
		String loginId = null;
		String loginPw = null;
		System.out.println("== 로그인 ==");

		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요");
				continue;
			}

			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

			if (isLoginIdDup == false) {
				System.out.printf("%s은(는) 존재하지 않는 아이디입니다\n", loginId);
				continue;
			}

			break;
		}

		Member member = memberService.getMemberByLoginId(loginId);

		int tryCount = 0;
		int tryMaxCount = 3;

		while (true) {
			if (tryCount >= tryMaxCount) {
				System.out.println("비밀번호를 확인하고 다시 시도해주세요");
				break;
			}

			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				tryCount++;
				System.out.println("비밀번호를 입력해주세요");
				continue;
			}

			if (member.loginPw.equals(loginPw) == false) {
				tryCount++;
				System.out.println("비밀번호가 일치하지 않습니다");
				continue;
			}
			
			// 로그인 member 저장
			// -1를 멈버의 아이디로
			//세션 멤머 객체에 멤버 정보를 저장
			this.member = member;
			System.out.printf("%s님 환영합니다\n", member.name);
			break;

		}

	}

	public void showProfile(String cmd) {
		//로그인 했는지 여부 확인
		if (member == null) {
			System.out.println("로그인 필요");
			
		}
		

		// 로그인 되어 있으면, 로그인 대상이 누군지 확인 ==>??
		
		//
		System.out.printf("== %s 회원 정보 ==\n", member.loginId);
		System.out.printf("번호 : %d\n", member.id);
		System.out.printf("가입날짜 : %s\n", member.regDate);
		System.out.printf("이름 : %s\n", member.name);
		
		
	}

}