package com.io.system.boletim.Enums;

public enum StatusAluno {
    REPROVADO(1, "REPROVADO"),
    RECUPERACAO(2, "RECUPERACAO"),
    APROVADO(3, "APROVADO"),
    INDEFINIDO(4,"INDEFINIDO");


    private int codigo;
    private String descricao;

    private StatusAluno(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static StatusAluno toEnum(Integer codigo){
        if(codigo == null){
            return null;
        }
        for(StatusAluno sa : values()){
            if(codigo.equals(sa.getCodigo())){
                return sa;
            }
        }
        throw new IllegalArgumentException("Id do Status inválido : " + codigo);
    }
}
