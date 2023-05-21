package hcmute.edu.vn.finalproject20110451.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtils {
    public static Pageable createPageable(int page, int size, String sortColumn, String sort) {
        Sort sortable = Sort.by(sortColumn).descending();
        if (sort.trim().equalsIgnoreCase("asc"))
            sortable = Sort.by(sortColumn).ascending();
        return PageRequest.of(page, size, sortable);
    }

}
