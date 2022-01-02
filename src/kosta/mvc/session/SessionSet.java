package kosta.mvc.session;

import java.util.HashSet;
import java.util.Set;

/**여러 user들을 관리하는 객체 */

public class SessionSet {//싱글톤 (메모리에 이걸 하나만 만들기) 

	private static SessionSet ss = new SessionSet();
	
	private Set<Session> set; //여러 user들을 저장해놓을 자료구조 
	
	private SessionSet() {
		set = new HashSet<>();
	}
	
	public static SessionSet getInstance() {
		return ss;
	}
	
	
	/**
	 * 사용자 찾기
	 * */
	public Session get(String sessionId) {
		for(Session session : set) { //세션 모아둔 set에서 session들을 하나씩 꺼냄 
			if(session.getSessionId().equals(sessionId)) {
				return session;
			}
		}
		return null;
	}
	
	//세션 객체들 반환
		public Set<Session> getSet(){
			return set;
		}
	
		/**
		 * 로그인 된 사용자 추가
		 * */
		public void add(Session session) {
			set.add(session);
		}
		
	/**
	 * 사용자 제거 - 로그아웃
	 * */
	public void remove(Session session) {
		set.remove(session);
	}
	
	
}
