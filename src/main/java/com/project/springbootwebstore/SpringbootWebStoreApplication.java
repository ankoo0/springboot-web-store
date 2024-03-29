package com.project.springbootwebstore;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
//@EnableJpaRepositories(repositoryBaseClass = SearchRepositoryImpl.class)
public class SpringbootWebStoreApplication {
	private static ConfigurableApplicationContext applicationContext;


//	static ProductService service = new ProductService();

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringbootWebStoreApplication.class,args);



		System.out.println(new int[0] instanceof Object);
//		applicationContext = SpringApplication.run(SpringbootWebStoreApplication.class, args);
//		final String indexPath = "C:\\Users\\PC\\Desktop\\My Projects\\springboot-web-store\\data\\index";
//		final String docsPath = "C:\\Users\\PC\\Desktop\\My Projects\\springboot-web-store\\data\\index";
//		final Path docDir = Paths.get(docsPath);
//		Directory dir = FSDirectory.open(Paths.get(indexPath));
//		Analyzer analyzer = new StandardAnalyzer();
//		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
//		iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
//		iwc.setCodec(new SimpleTextCodec());
//		System.out.println(iwc.getCodec().getName());
//		try ( IndexWriter writer = new IndexWriter(dir, iwc)) {
////			writer.
////			// read documents, and write index data:
////			indexDocs(writer, docDir);
//		}
////		displayAllBeans();
	}

	public static void displayAllBeans() {
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		for(String beanName : allBeanNames) {
			System.out.println(beanName);
		}
	}

}
