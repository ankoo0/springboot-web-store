package com.project.springbootwebstore;

import com.project.springbootwebstore.model.repository.SearchRepositoryImpl;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.codecs.simpletext.SimpleTextCodec;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = SearchRepositoryImpl.class)
public class SpringbootWebStoreApplication {
	private static ConfigurableApplicationContext applicationContext;

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
