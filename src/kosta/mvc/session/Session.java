package kosta.mvc.session;

/**
 * 사용자 객체
 * 
 * 로그인된 사용자의 정보를, 로그인이 유지되는 동안 Session객체에 저장해서 유지한다. 
 * 
 * */
public class Session {
	private String sessionId;
	private String seller;
	
	
	public Session() {}
	public Session(String sessionId) {
		this.sessionId = sessionId;
	}
	public Session(String sessionId, String seller) {
		this(sessionId);
		this.seller = seller;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Session [sessionId=");
		builder.append(sessionId);
		builder.append(", seller=");
		builder.append(seller);
		builder.append("]");
		return builder.toString();
	}
	/**
	 * 동일한 session이 동시에 로그인되지 않도록 확인한다. */
	@Override
	public int hashCode() {
		return sessionId.hashCode();
	}
	
	/**
	 * 같은 객체라는 뜻은 hashCode가 같아야하고,
	 * equals의 결과가 true여야한다.
	 * 
	 *  hash코드가 다르면 무조건 다른 객체
	 *  hash코드가 같으면 같은 객체일수도, 다른 객체일수도 있다.
	 * */
	@Override
	public boolean equals(Object obj) {
		Session other = (Session) obj;
		if(sessionId.equals(other.sessionId)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
//		return result;
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Session other = (Session) obj;
//		if (sessionId == null) {
//			if (other.sessionId != null)
//				return false;
//		} else if (!sessionId.equals(other.sessionId))
//			return false;
//		return true;
//	}
	
	
	
	
	
}
