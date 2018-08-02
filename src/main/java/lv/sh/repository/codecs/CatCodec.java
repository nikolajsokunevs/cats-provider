package lv.sh.repository.codecs;

import lv.sh.dto.Cat;
import org.bson.*;
import org.bson.codecs.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class CatCodec implements CollectibleCodec<Cat> {

    private Codec<Document> documentCodec;

    public CatCodec() {
        this.documentCodec = new DocumentCodec();
    }

    public CatCodec(Codec<Document> codec) {
        this.documentCodec = codec;
    }

    @Override
    public void encode(BsonWriter writer, Cat value,
                       EncoderContext encoderContext) {
        Document document = catToDocument(value);
        documentCodec.encode(writer, document, encoderContext);
    }

    public Document catToDocument(Cat cat) {
        Document document = new Document();
        String id = cat.getId();
        String name = cat.getName();
        String lastname = cat.getLastname();

        String breed = cat.getBreed();
        Integer age = cat.getAge();
        List<String> vaccination = cat.getVaccination();
        List<Cat> ancestors = cat.getAncestors();


        if (null != name) document.put("_id", id);
        if (null != name) document.put("name", name);
        if (null != lastname) document.put("lastname", lastname);
        if (null != breed) document.put("breed", breed);
        if (null != age) document.put("age", age);
        if (null != vaccination) document.put("vaccination", vaccination);
        if (null != ancestors) {
            List<Document> ancestorsD=new ArrayList<>();
            for (Cat current : ancestors) {
                ancestorsD.add(catToDocument(generateIdIfAbsentFromDocument(current)));
            }
            document.put("ancestors", ancestorsD);
        }
        return document;
    }


    @Override
    public Class<Cat> getEncoderClass() {
        return Cat.class;
    }

    @Override
    public Cat decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = documentCodec.decode(reader, decoderContext);
        System.out.println("document " + document);
        return documentToCat(document);
    }

    public Cat documentToCat(Document document) {
        Cat cat = new Cat();
        cat.setId(document.getString("_id"));
        cat.setName(document.getString("name"));
        cat.setLastname(document.getString("lastname"));
        cat.setBreed(document.getString("breed"));
        cat.setAge(document.getInteger("age"));
        cat.setVaccination((List<String>) document.get("vaccination"));

        //cat.setAncestors((List<Cat>) document.get("ancestors"));
        List<Document> ancestorsD=((List<Document>) document.get("ancestors"));
        if (ancestorsD!=null) {
            List<Cat> ancestors = new ArrayList<>();
            if (ancestorsD.size()>0){
                for (Document current : ancestorsD) {
                    ancestors.add(documentToCat(current));
                }
            }
            cat.setAncestors(ancestors);
        }
        return cat;
    }

    @Override
    public Cat generateIdIfAbsentFromDocument(Cat document) {
        Cat catWithId = document;
        if (catWithId.getId() == null) catWithId.setId(new ObjectId().toString());
        return catWithId;
    }

    @Override
    public boolean documentHasId(Cat document) {
        return null == document.getId();
    }

    @Override
    public BsonValue getDocumentId(Cat document) {
        if (!documentHasId(document)) {
            throw new IllegalStateException("The document does not contain an _id");
        }
        return new BsonString(document.getId());
    }
}