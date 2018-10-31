package ufc.cmu.promocity.backend.model;

public class Coupon {
	private Long id;
	private String description;
	private String qrCode;
	
	public Coupon() {
		
	}
	
	public Coupon(Long id, String description, String qrCode) {
		super();
		this.id = id;
		this.description = description;
		this.qrCode = qrCode;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
}
