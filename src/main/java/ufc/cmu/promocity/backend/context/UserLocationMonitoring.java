package ufc.cmu.promocity.backend.context;

import java.util.List;

import ufc.cmu.promocity.backend.model.Store;
import ufc.cmu.promocity.backend.model.Users;
import ufc.cmu.promocity.backend.utils.geographic.GPSPoint;
import ufc.cmu.promocity.backend.utils.geographic.GeographicArea;

/**
 * Classe responsavel por ficar monitorando a localizacao do usuario dentro da area de promocoes
 * obs: A area de promocao já deve ter sido criada com todas as lojas registradas
 * O usuário só recebe uma promoção de uma loja uma vez, ou seja, é preciso controlar o 
 *  envio de cupons para os usuários
 *  * O raio de proximidade é fixo (1 km) para todas lojas registrada
 *  s
 * @author armandosoaressousa
 *
 */
public class UserLocationMonitoring{
	private PromotionArea promotionArea;
	private CouponsSent couponsSent;
	private double radius=1;
	private long idStore=0;
	
	public UserLocationMonitoring(PromotionArea promotionArea) {
		this.promotionArea = promotionArea;
	}
	
	/**
	 * Checa a proximidade do usuario dentro de uma lista de areas de promocoes
	 * @param userLocation
	 */
	public void checkNearby(Users user) {
		List<Store> listOfStoreRegistered = this.promotionArea.getStoreAreasRegistered();
		
		for (Store element : listOfStoreRegistered) {				
			//calcula a distancia entre a localizacao do usuario e da localizacao
			double userDistance = new GeographicArea().distance(element.getLatitude(), element.getLongitude(), user.getLatitude(), user.getLongitude());
			 
			//Veriica se o usuário ainda NÃO recebeu cupom dessa loja
			boolean checkCoupon = userNoReceivedPromotionStore(element.getId(), user.getId()); 
			
			//se for menor que 1 (km) dispara uma mensagem para o usuario com a promocao da loja
			//também adiciona este(s) cupom(s) no referido usuário
			if (userDistance < radius && checkCoupon) {
				//recupera a promocao/cupom da loja
				//envia mensagem para usuario com o cupom da promocao	
				setIdStore(element.getId());
				Object coupon = "Cupom teste da loja " + element.getName(); 
				Object content = "Conteudo teste";
				new Messenger().sendPromotion(String.valueOf(user.getId()) + user.getUsername(), coupon, content);
			}
		}
		
	}
	
	private boolean userNoReceivedPromotionStore(Long idStore, Long idUser) {
		boolean newCoupon=false;
		//TODO falta implementar a regra...
		//checa se o usuário já recebeu uma promocao da loja
		newCoupon = true;
		
		return newCoupon;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public PromotionArea getPromotionArea() {
		return promotionArea;
	}

	public void setPromotionArea(PromotionArea promotionArea) {
		this.promotionArea = promotionArea;
	}

	public long getIdStore() {
		return idStore;
	}

	public void setIdStore(Long long1) {
		this.idStore = long1;
	}

}
