public class Main {
	public static void main(String[] args) throws InterruptedException {
		Directory root = Directory.createEmpty();
		root.createDirectory("boot").getAsDirectory()
				.createFile("kernel", "1101001001001101001");
		Entry etc = root.createDirectory("etc");
		Entry motd = etc.getAsDirectory().createFile("motd", "");
		Entry friendlyMessage = etc.getAsDirectory()
				.createHardlink("friendly-message.txt", motd);

		root.accept("", new Printer());

		System.out.println();
		Thread.sleep(42);
		friendlyMessage.getAsFile().writeContent("Welcome =)");

		root.accept("", new Printer());
	}
}

