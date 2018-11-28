package ufc.cmu.promocity.backend.context;

import java.util.LinkedList;
import java.util.List;

import ufc.cmu.promocity.backend.model.Coupon;
import ufc.cmu.promocity.backend.model.Promotion;
import ufc.cmu.promocity.backend.model.Store;
import ufc.cmu.promocity.backend.model.Users;
import ufc.cmu.promocity.backend.report.ReportApplication;
import ufc.cmu.promocity.backend.report.ReportCoupon;
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
	private List<Long> idStoreList;
	private List<Coupon> listaDeCuponsColetados = new LinkedList<Coupon>();
	private List<ReportCoupon> listaDeReportCupomColetados = new LinkedList<>();
	
	public UserLocationMonitoring(PromotionArea promotionArea) {
		this.promotionArea = promotionArea;
	}
	
	/**
	 * Checa a proximidade do usuario dentro de uma lista de areas de promocoes
	 * @param userLocation
	 * 
    /**@deprecated  Esse método não deve ser mais utilizado, pois a forma de checar o contexto de proximidade do usuário em relação a loja mudou <br/>
    *              {will be removed in next version} <br/>
    *              	use {@link #checkUserContext(Users user)} instead like this: 
    * 
    * <blockquote><pre>
    * checkUserContext(Users user)
    * </pre></blockquote>
    *
    * @deprecated use {@link #new()} instead.  
    */
   @Deprecated
	public void checkNearby(Users user) {
		List<Store> listOfStoreRegistered = this.promotionArea.getStoreAreasRegistered();
		List<Long> listaIdLojasProximas = new LinkedList<>();
		
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
				listaIdLojasProximas.add(element.getId());			
				Object coupon = "Cupom teste da loja " + element.getName(); 
				Object content = "Conteudo teste";
				new Messenger().sendPromotion(String.valueOf(user.getId()) + user.getUsername(), coupon, content);
			}
		}
		this.setIdStoreList(listaIdLojasProximas);
	}
	
	/**
	 * Checa o contexto de proximidade do usuário em relação as lojas que tenham promoções ativas
	 * @param user
	 */
	public void checkUserContext(Users user) {
		List<Store> listaDeLojasComPromocoesRegistradas = this.promotionArea.getStoreAreasRegistered();
		//recupera os cupons das promocoes da loja
		List<Coupon> listaCuponsDaPromocaoDaLojaCorrente = new LinkedList<Coupon>();
		
		//1 Recebe a localização do usuário
		
		//2 Para cada loja registrada com promoções verifica a proximidade do usuário
		for (Store loja : listaDeLojasComPromocoesRegistradas) {	

			//2.1 Checa se o usuário tá perto -> calcula a distancia entre a localizacao do usuario e da localizacao
			double userDistance = new GeographicArea().distance(loja.getLatitude(), loja.getLongitude(), user.getLatitude(), user.getLongitude());
			
			//2.1.1 usuário próximo -> se usuário estiver em um raio de 1km da loja 
			if (userDistance < radius) {
				//recupera as promocoes da loja
				List<Promotion> listaDePromocoesDaLojaCorrente = loja.getPromotionList();				

				//percorre cada promocao para checar os cupons
				for(Promotion promocao : listaDePromocoesDaLojaCorrente) {
					List<Coupon> listaAuxiliarDeCupons = promocao.getCoupons();
					for (Coupon cupomAuxiliar : listaAuxiliarDeCupons) {												
						//2.1.1.1 Checa se o usuário já tem o cupom
						if (user.alreadyCoupon(cupomAuxiliar)) {
							// Tem cupom -> sai da loja atual e vai para próxima (continue)
							continue;
						}else {
							// Não tem cupom -> faz a coleta do cupom da loja
							listaCuponsDaPromocaoDaLojaCorrente.add(cupomAuxiliar);
							ReportCoupon cupomDetalhado = new ReportCoupon(loja, promocao, cupomAuxiliar);
							this.listaDeReportCupomColetados.add(cupomDetalhado);
						}						
					}
				}
																		
			}else {
				//2.1.2 usuário longe -> sai da loja atual e vai para a próxima (continue)
				continue;
			}								
		}				
		this.setListaDeCuponsColetados(listaCuponsDaPromocaoDaLojaCorrente);				
	}
	
    /**@deprecated  Esse método não deve ser mais utilizado, pois a forma de checar o contexto de proximidade do usuário em relação a loja mudou <br/>
    *              {will be removed in next version} <br/>
    *                            	  
    * @deprecated use {@link #new()} instead.  
    */
   @Deprecated
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

	public List<Long> getIdStoreList() {
		return idStoreList;
	}

	public void setIdStoreList(List<Long> idStoreList) {
		this.idStoreList = idStoreList;
	}

	public List<Coupon> getListaDeCuponsColetados() {
		return listaDeCuponsColetados;
	}

	public void setListaDeCuponsColetados(List<Coupon> listaDeCuponsColetados) {
		this.listaDeCuponsColetados = listaDeCuponsColetados;
	}

	public List<ReportCoupon> getListaDeReportCupomColetados() {
		return listaDeReportCupomColetados;
	}

	public void setListaDeReportCupomColetados(List<ReportCoupon> listaDeReportCupomColetados) {
		this.listaDeReportCupomColetados = listaDeReportCupomColetados;
	}

}
