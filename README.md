# spring-data-elasticsearch-practice

1. 엘라스틱 서치 이미지 다운 docker pull docker.elastic.co/elasticsearch/elasticsearch:7.10.0
2. 엘라스틱 서치 도커로 실행 docker run -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node"
   docker.elastic.co/elasticsearch/elasticsearch:7.10.0
3. build.gradle 의존성 추가 implementation 'org.springframework.data:spring-data-elasticsearch:4.2.2'
4. 엘라스틱 서치 환경설정

```aidl
@EnableElasticsearchRepositories(basePackageClasses = {UserSearchRepository.class, ProductSearchRepository.class})
@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .build();
        return RestClients.create(clientConfiguration).rest();
    }
}

```

5. 로깅 추가 logging.level.org.springframework.data.elasticsearch.client.WIRE=TRACE
6. @Entity 처럼 @Document 를 통해 엘라스틱 서치 인덱스 매핑
```aidl
@Entity
@Document(indexName = "products")
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private int price;
    private String name;
    private String description;

    @Builder
    public Product(int price, String name, String description) {
        this.id = null;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    protected Product() {

    }
}

```
7. 엘라스틱서치 데이터 리포지토리 생성 jpa리포지토리랑 거의 같다. 페이징 처리도 가능
```aidl
public interface ProductSearchRepository extends ElasticsearchRepository<Product, Long> {
    List<Product> findAllByName(String name);
}
```
8. spring data jpa와 elastic search를 함께 연동할 경우 동일한 엔티티에 대해 리포지토리 스캔이 두번 돼서 오류가 발생한다. 따라서 해당 어노테이션을 붙여 JPA 리포지토리 스캔시 JPA 리포지토리만 스캔하도록 한다.
```aidl
@EnableJpaRepositories(excludeFilters={
        @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,
        value = UserSearchRepository.class),
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
    value = ProductSearchRepository.class)})
@SpringBootApplication
public class ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }

}

```