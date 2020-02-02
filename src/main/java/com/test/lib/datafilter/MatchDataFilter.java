package com.test.lib.datafilter;

import com.test.entity.MatchData;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Setter
public class MatchDataFilter implements Specification<MatchData> {

    private String statusType;
    private String liveStatus;

    @Override
    public Predicate toPredicate(Root<MatchData> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        Predicate result = criteriaBuilder.conjunction();

        if (this.statusType != null) {
            result = criteriaBuilder.and(result, criteriaBuilder.equal(root.get("status").get("type"), this.statusType));
        }

        if (this.liveStatus != null) {
            result = criteriaBuilder.and(result, criteriaBuilder.equal(root.get("liveStatus"), this.liveStatus));
        }

        return result;
    }
}
