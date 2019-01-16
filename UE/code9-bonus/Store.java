public class Store {
	public static void main(String[] args) {
		System.out.println(toPriceTag(new Article("Dog Food", 1000)));
		System.out.println(toPriceTag(new Article("Pizza", 1000)));
		System.out.println(toPriceTag(new Article("Pizza", 100)));
	}

	private static String toPriceTag(Article article) {
		return Optional.present(article)
                .filter(Article::isHumanEatable)
                .map(Article::adjustPrice)
                .fold(a -> "This article named " + a.getName() + " costs " + a.getPrice() + " cent.", () -> "This Article is unavailable.");

	}
}

