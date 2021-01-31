package pattern.eventbus;




/**
 * Created by HuGuodong on 3/13/20.
 */


public class RegPromotionObserver {

  private PromotionService promotionService; // 依赖注入


  public RegPromotionObserver() {
    promotionService = new PromotionService();
  }

  @Subscrible
  public void handleRegSuccess(Long userId) {
    System.out.println("handleRegSuccess...");
    promotionService.issueNewUserExperienceCash(userId);
  }
}

