String.prototype.replaceAll = function (s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
};

function getCNWord(str) {
    var cnReg = /[\u4e00-\u9fa5]+.*[\u4e00-\u9fa5]/g;
    var words = str.match(cnReg);
    if (words != null && words.length > 0) {
        return words[0];
    } else {
        return '';
    }
}

function getNumber(str) {
    var cnReg = /[0-9]+/g;
    var nums = str.match(cnReg);
    if (nums != null && nums.length > 0) {
        return Number(nums[0]);
    } else {
        return null;
    }
}