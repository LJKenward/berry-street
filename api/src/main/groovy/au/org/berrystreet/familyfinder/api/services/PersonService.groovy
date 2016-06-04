package au.org.berrystreet.familyfinder.api.services

import au.org.berrystreet.familyfinder.api.controller.requests.PersonChangeRequest
import au.org.berrystreet.familyfinder.api.controller.requests.RelationshipRequest
import au.org.berrystreet.familyfinder.api.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PersonService {

    @Autowired PersonRepository personRepository;

    Long create(PersonChangeRequest personChangeRequest) {
        12345
    }

    def update(long personId, PersonChangeRequest personChangeRequest) {

    }

    def findRelationshipsForPerson(long personId) {
        [
                [
                        id: 51,
                        name: 'Bertha',
                        relationship: 'Aunt'
                ],
                [
                        id: 52,
                        name: 'Rob',
                        relationship: 'Uncle'
                ]
        ]
    }

    def createRelationship(long fromPerson, long toPerson, RelationshipRequest relationshipRequest) {
        4321
    }

    private Map<String, Object> toD3Format(Iterator<Map<String, Object>> result) {
        List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> rels= new ArrayList<Map<String,Object>>();
        int i=0;
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            nodes.add(map("title",row.get("movie"),"label","movie"));
            int target=i;
            i++;
            for (Object name : (Collection) /*(String[])*/ row.get("cast")) {
                Map<String, Object> actor = map("title", name,"label","actor");
                int source = nodes.indexOf(actor);
                if (source == -1) {
                    nodes.add(actor);
                    source = i++;
                }
                rels.add(map("source",source,"target",target));
            }
        }
        return map("nodes", nodes, "links", rels);
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String,Object>(2);
        result.put(key1,value1);
        result.put(key2,value2);
        return result;
    }

    public Map<String, Object> graph(int limit) {
        Iterator<Map<String, Object>> result = personRepository.graph(limit).iterator();
        return toD3Format(result);
    }

}
