import React, { useEffect } from 'react';

const Pagination = ({ currentPage, totalPage, onPageChange }) => {

    return (
        <div className="btn-group mt-5" role="group" aria-label="Basic example">
            <button type="button"
                className="btn btn-outline-secondary" onClick={() => onPageChange(1)}>&#8592;&#8592;</button>
            <button type="button"
                className="btn btn-outline-secondary" onClick={() => onPageChange(currentPage-1)}>&#8592;</button>
            <button type="button"
                className="btn btn-outline-secondary">{currentPage}</button>
            <button type="button"
                className="btn btn-outline-secondary" onClick={() => onPageChange(currentPage+1)}>&#8594;</button>
            <button type="button"
                className="btn btn-outline-secondary" onClick={() => onPageChange(totalPage)}>&#8594;&#8594;</button>

        </div>
    );
};

export default Pagination;